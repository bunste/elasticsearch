/*
 * Copyright Elasticsearch B.V. and/or licensed to Elasticsearch B.V. under one
 * or more contributor license agreements. Licensed under the Elastic License
 * 2.0; you may not use this file except in compliance with the Elastic License
 * 2.0.
 */

package org.elasticsearch.xpack.esql.expression.function;

import org.elasticsearch.xpack.esql.expression.function.aggregate.Avg;
import org.elasticsearch.xpack.esql.expression.function.aggregate.Count;
import org.elasticsearch.xpack.esql.expression.function.aggregate.Max;
import org.elasticsearch.xpack.esql.expression.function.aggregate.Median;
import org.elasticsearch.xpack.esql.expression.function.aggregate.MedianAbsoluteDeviation;
import org.elasticsearch.xpack.esql.expression.function.aggregate.Min;
import org.elasticsearch.xpack.esql.expression.function.aggregate.Sum;
import org.elasticsearch.xpack.esql.expression.function.scalar.date.DateFormat;
import org.elasticsearch.xpack.esql.expression.function.scalar.math.Abs;
import org.elasticsearch.xpack.esql.expression.function.scalar.math.Round;
import org.elasticsearch.xpack.esql.expression.function.scalar.string.Length;
import org.elasticsearch.xpack.esql.expression.function.scalar.string.StartsWith;
import org.elasticsearch.xpack.ql.expression.function.FunctionDefinition;
import org.elasticsearch.xpack.ql.expression.function.FunctionRegistry;

import java.util.Locale;

public class EsqlFunctionRegistry extends FunctionRegistry {

    public EsqlFunctionRegistry() {
        register(functions());
    }

    EsqlFunctionRegistry(FunctionDefinition... functions) {
        register(functions);
    }

    private FunctionDefinition[][] functions() {
        return new FunctionDefinition[][] {
            new FunctionDefinition[] {
                def(Avg.class, Avg::new, "avg"),
                def(Count.class, Count::new, "count"),
                def(Max.class, Max::new, "max"),
                def(Median.class, Median::new, "median"),
                def(MedianAbsoluteDeviation.class, MedianAbsoluteDeviation::new, "median_absolute_deviation"),
                def(Min.class, Min::new, "min"),
                def(Sum.class, Sum::new, "sum") },
            // math
            new FunctionDefinition[] { def(Abs.class, Abs::new, "abs") },
            new FunctionDefinition[] { def(Round.class, Round::new, "round") },
            // string
            new FunctionDefinition[] { def(Length.class, Length::new, "length") },
            // date
            new FunctionDefinition[] {
                def(DateFormat.class, DateFormat::new, "date_format"),
                def(StartsWith.class, StartsWith::new, "starts_with") } };
    }

    @Override
    protected String normalize(String name) {
        return name.toLowerCase(Locale.ROOT);
    }
}
