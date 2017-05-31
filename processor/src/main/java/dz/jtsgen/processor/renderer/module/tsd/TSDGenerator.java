/*
 * Copyright (c) 2017 Dragan Zuvic
 *
 * This file is part of jtsgen.
 *
 * jtsgen is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * jtsgen is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with jtsgen.  If not, see http://www.gnu.org/licenses/
 *
 */

package dz.jtsgen.processor.renderer.module.tsd;

import dz.jtsgen.processor.helper.IdentHelper;
import dz.jtsgen.processor.model.TSModuleInfo;
import dz.jtsgen.processor.model.TSNameSpace;
import dz.jtsgen.processor.model.TSType;
import dz.jtsgen.processor.model.rendering.TSTypeVisitor;
import dz.jtsgen.processor.renderer.helper.ModuleResourceHelper;
import dz.jtsgen.processor.renderer.helper.PrintWriterWithLogging;
import dz.jtsgen.processor.renderer.model.TypeScriptRenderModel;

import javax.annotation.processing.ProcessingEnvironment;
import javax.tools.FileObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Logger;

public final class TSDGenerator {

    private static Logger LOG = Logger.getLogger(TSDGenerator.class.getName());

    private TypeScriptRenderModel model;
    private ProcessingEnvironment env;


    public TSDGenerator(TypeScriptRenderModel model, ProcessingEnvironment env) {
        this.model = model;
        this.env = env;
    }


    public void writeTypes() throws IOException {
        TSModuleInfo module=model.getModuleInfo();
        TSNameSpace nameSpace = convertToNameSpace(model.getTsTypesByModule(module));
        FileObject dts_file_object = ModuleResourceHelper.createResource(env, module, model.fileByOutputType());

        try (PrintWriter out = new PrintWriterWithLogging(dts_file_object.openWriter(), model.fileByOutputType())) {
            writeHeader(module, out);
            writeUMD(module, out);
            writeNamespace(module, nameSpace, out);
        }
    }

    private TSNameSpace convertToNameSpace(List<TSType> types) {
        TSNameSpace result = new TSNameSpace();
        for (TSType type : types) {
            TSNameSpace subNode = result.findOrCreate(type.getNamespace());
            subNode.addType(type);
        }
        return result;
    }

    private void writeNamespace(TSModuleInfo module, TSNameSpace nameSpace, PrintWriter out) {
        LOG.finest(() -> "TSDGen: writing module namespaces " + nameSpace);
        out.println("");

        writeNameSpaces(module, nameSpace.findOrCreate(""), 0, out);
    }

    private void writeNameSpaces(TSModuleInfo module, TSNameSpace ns, int ident, PrintWriter out) {
        if (ns.isRoot()) {
            out.println(this.model.moduleDeclaration());
            outputTypes(module,  1, ns.getTypes(), out);
            ns.getSubNamespaces().forEach(x -> writeNameSpaces(module, ns.getSubNameSapce(x), 1, out));
            out.println(" }");
        } else {
            final String prefix = IdentHelper.identPrefix(ident);
            out.print(prefix);
            out.print("export namespace ");
            out.print(ns.getName());
            out.println(" {");
            ns.getSubNamespaces().forEach(x -> writeNameSpaces(module, ns.getSubNameSapce(x), ident + 1, out));
            outputTypes(module, ident + 1, ns.getTypes(), out);
            out.print(prefix);
            out.println("}");
        }
    }

    private void outputTypes(TSModuleInfo module, int ident, List<TSType> types, PrintWriter out) {
        if (types.isEmpty()) return;

        TSTypeVisitor tsTypeVisitor = new DefaultTSTypeVisitor(out);
        types.forEach(x -> x.accept(tsTypeVisitor, ident));
    }

    private void writeHeader(TSModuleInfo module, PrintWriter out) {
        out.println(String.format("// Type definitions for %s %s generated by jtsgen", module.getModuleName(), module.getModuleVersion()));
        out.println(String.format("// Definitions by: %s <%s>", module.getModuleAuthor(), module.getModuleAuthorUrl()));
        out.println("");
    }

    private void writeUMD(TSModuleInfo module, PrintWriter out) {
        if (module.getUmdVariableName().isPresent()) {
            out.println("");
            out.println("// UMD enabled");
            out.println(String.format("export as namespace %s;", module.getUmdVariableName().get()));
        }
    }
}
