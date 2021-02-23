/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2021 Yegor Bugayenko
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package org.cactoos.io;

import java.nio.file.Files;
import java.nio.file.Path;
import org.eolang.phi.AtBound;
import org.eolang.phi.AtFree;
import org.eolang.phi.AtLambda;
import org.eolang.phi.Data;
import org.eolang.phi.Datarized;
import org.eolang.phi.PhDefault;
import org.eolang.phi.PhEta;
import org.eolang.phi.PhWith;
import org.eolang.phi.Phi;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

/**
 * Test.
 *
 * @since 0.1
 * @checkstyle TypeNameCheck (100 lines)
 */
public final class EOfilesEOφTest {

    @Test
    public void listsFilesInDirectory() throws Exception {
        final Path temp = Files.createTempDirectory("test");
        final Path file = temp.resolve("a.txt");
        Files.write(file, "Hello, world!".getBytes());
        final Phi[] filters = new Phi[1];
        filters[0] = new Filter(new PhEta());
        final Phi[] array = new Datarized(
            new PhWith(
                new PhWith(
                    new EOfiles(),
                    "filters",
                    new Data.ToPhi(filters)
                ),
                "path",
                new Data.ToPhi(temp.toFile().toString())
            )
        ).take(Phi[].class);
        MatcherAssert.assertThat(
            new Datarized(array[1]).take(String.class),
            Matchers.endsWith(".txt")
        );
        MatcherAssert.assertThat(array, Matchers.arrayWithSize(2));
    }

    /**
     * Test filter.
     *
     * @since 0.1
     */
    public final class Filter extends PhDefault {
        /**
         * Ctor.
         * @param parent The parent
         */
        @SuppressWarnings(
            "PMD.ConstructorOnlyInitializesOrCallOtherConstructors"
        )
        public Filter(final Phi parent) {
            super(parent);
            this.add("x", new AtFree());
            this.add(
                "φ",
                new AtBound(
                    new AtLambda(this, self -> new Data.ToPhi(true))
                )
            );
        }

    }

}

