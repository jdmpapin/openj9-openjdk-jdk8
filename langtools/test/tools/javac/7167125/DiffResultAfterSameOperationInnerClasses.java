/*
 * Copyright (c) 2002, 2013, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */

/*
 * @test
 * @bug 7167125
 * @summary Two variables after the same operation in a inner class return
 * different results
 * @run main DiffResultAfterSameOperationInnerClasses
 */

public class DiffResultAfterSameOperationInnerClasses {
    public int i = 1;
    private int j = 1;
    public String s1 = "Hi, ";
    private String s2 = "Hi, ";

    public static void main(String[] args) {
        InnerClass inner =
                new DiffResultAfterSameOperationInnerClasses().new InnerClass();
        if (!inner.test()) {
            throw new AssertionError("Different results after same calculation");
        }
    }

    class InnerClass {
        public boolean test() {
            i += i += 1;
            j += j += 1;

            s1 += s1 += "dude";
            s2 += s2 += "dude";

            System.out.println("s1 = " + s1);
            System.out.println("s2 = " + s2);

            return (i == j && i == 3 &&
                    s1.equals(s2) && s1.endsWith("Hi, Hi, dude"));
        }
    }
}
