/*
 * Copyright (C) 2007-2010 Júlio Vilmar Gesser.
 * Copyright (C) 2011, 2013-2016 The JavaParser Team.
 *
 * This file is part of JavaParser.
 *
 * JavaParser can be used either under the terms of
 * a) the GNU Lesser General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 * b) the terms of the Apache License
 *
 * You should have received a copy of both licenses in LICENCE.LGPL and
 * LICENCE.APACHE. Please refer to those files for details.
 *
 * JavaParser is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 */

package com.github.javaparser.printer;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;
import org.junit.Test;

import static com.github.javaparser.printer.PrettyPrintVisitor.getMaximumCommonType;
import static org.junit.Assert.assertEquals;

public class PrettyPrintVisitorTest {

    @Test
    public void getMaximumCommonTypeWithoutAnnotations() {
        VariableDeclarationExpr vde1 = JavaParser.parseVariableDeclarationExpr("int a[], b[]");
        assertEquals("int[]", getMaximumCommonType(vde1).toString());

        VariableDeclarationExpr vde2 = JavaParser.parseVariableDeclarationExpr("int[][] a[], b[]");
        assertEquals("int[][][]", getMaximumCommonType(vde2).toString());

        VariableDeclarationExpr vde3 = JavaParser.parseVariableDeclarationExpr("int[][] a, b[]");
        assertEquals("int[][]", getMaximumCommonType(vde3).toString());
    }

    @Test
    public void getMaximumCommonTypeWithAnnotations() {
        VariableDeclarationExpr vde1 = JavaParser.parseVariableDeclarationExpr("int a @Foo [], b[]");
        assertEquals("int", getMaximumCommonType(vde1).toString());

        VariableDeclarationExpr vde2 = JavaParser.parseVariableDeclarationExpr("int[]@Foo [] a[], b[]");
        assertEquals("int[] @Foo [][]", getMaximumCommonType(vde2).toString());
    }
}
