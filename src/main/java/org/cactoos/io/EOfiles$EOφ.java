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
import java.nio.file.Paths;
import org.eolang.phi.AtBound;
import org.eolang.phi.AtLambda;
import org.eolang.phi.Data;
import org.eolang.phi.Datarized;
import org.eolang.phi.PhCopy;
import org.eolang.phi.PhDefault;
import org.eolang.phi.PhWith;
import org.eolang.phi.Phi;

/**
 * Files.φ.
 *
 * @since 0.1
 * @checkstyle TypeNameCheck (100 lines)
 */
@SuppressWarnings("PMD.AvoidDollarSigns")
public class EOfiles$EOφ extends PhDefault {

    /**
     * Ctor.
     * @param parent The parent
     * @checkstyle BracketsStructureCheck (200 lines)
     */
    @SuppressWarnings("PMD.ConstructorOnlyInitializesOrCallOtherConstructors")
    public EOfiles$EOφ(final Phi parent) {
        super(parent);
        this.add("φ", new AtBound(new AtLambda(this, self -> {
            final String path = new Datarized(
                self.attr("ρ").get().attr("path").get()
            ).take(String.class);
            final Phi[] filters = new Datarized(
                self.attr("ρ").get().attr("filters").get()
            ).take(Phi[].class);
            return new Data.ToPhi(
                Files.walk(Paths.get(path))
                    .filter(
                        file -> EOfiles$EOφ.pass(
                            filters,
                            new PhWith(
                                new EOfile(),
                                0,
                                new Data.ToPhi(file.toFile().toString())
                            )
                        )
                    )
                    .map(
                        file -> new PhWith(
                            new EOfile(),
                            0,
                            new Data.ToPhi(file.toFile().toString())
                        )
                    )
                    .toArray(Phi[]::new)
            );
        })));
    }

    /**
     * Pass through all filters.
     * @param filters The filters
     * @param file One file
     * @return TRUE if pass
     */
    private static boolean pass(final Phi[] filters, final Phi file) {
        boolean pass = true;
        for (final Phi filter : filters) {
            pass = new Datarized(
                new PhWith(new PhCopy(filter), 0, file)
            ).take(Boolean.class);
            if (!pass) {
                break;
            }
        }
        return pass;
    }

}
