package com.app.test.williamchart.data

data class LineChartConfiguration(
        override val width: Int,
        override val height: Int,
        override val paddings: Paddings,
        override val axis: AxisType,
        override val labelsSize: Float,
        override val scale: Scale,
        override val labelsFormatter: (Float) -> String = { it.toString() },
        val lineThickness: Float,
        val pointsDrawableWidth: Int,
        val pointsDrawableHeight: Int,
        val fillColor: Int,
        val gradientFillColors: IntArray
) : ChartConfiguration(
        width = width,
        height = height,
        paddings = paddings,
        axis = axis,
        labelsSize = labelsSize,
        scale = scale,
        labelsFormatter = labelsFormatter
)
