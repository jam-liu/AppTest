package com.app.test.williamchart.view

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import androidx.annotation.DrawableRes
import androidx.annotation.Size
import android.util.AttributeSet
import com.app.test.R
import com.app.test.williamchart.ChartContract
import com.app.test.williamchart.animation.NoAnimation
import com.app.test.williamchart.data.*
import com.app.test.williamchart.extensions.*
import com.app.test.williamchart.renderer.LineChartRenderer

class LineChartView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : AxisChartView(context, attrs, defStyleAttr), ChartContract.LineView {

    @Suppress("MemberVisibilityCanBePrivate")
    var smooth: Boolean = defaultSmooth

    @Suppress("MemberVisibilityCanBePrivate")
    var lineThickness: Float = defaultLineThickness

    @Suppress("MemberVisibilityCanBePrivate")
    var fillColor: Int = defaultFillColor

    @Suppress("MemberVisibilityCanBePrivate")
    var lineColor: Int = defaultLineColor

    @Size(min = 2, max = 2)
    @Suppress("MemberVisibilityCanBePrivate")
    var gradientFillColors: IntArray = intArrayOf(0, 0)

    @DrawableRes
    @Suppress("MemberVisibilityCanBePrivate")
    var pointsDrawableRes = -1

    override val chartConfiguration: ChartConfiguration
        get() =
            LineChartConfiguration(
                    width = measuredWidth,
                    height = measuredHeight,
                    paddings = Paddings(
                            paddingLeft.toFloat(),
                            paddingTop.toFloat(),
                            paddingRight.toFloat(),
                            paddingBottom.toFloat()
                    ),
                    axis = axis,
                    labelsSize = labelsSize,
                    lineThickness = lineThickness,
                    scale = scale,
                    pointsDrawableWidth = if (pointsDrawableRes != -1)
                        getDrawable(pointsDrawableRes)!!.intrinsicWidth else -1,
                    pointsDrawableHeight = if (pointsDrawableRes != -1)
                        getDrawable(pointsDrawableRes)!!.intrinsicHeight else -1,
                    fillColor = fillColor,
                    gradientFillColors = gradientFillColors,
                    labelsFormatter = labelsFormatter
            )

    init {
        renderer = LineChartRenderer(this, painter, NoAnimation())
        handleAttributes(obtainStyledAttributes(attrs, R.styleable.LineChartAttrs))
        handleEditMode()
    }

    override fun drawLine(points: List<DataPoint>) {

        val linePath =
                if (!smooth) points.toLinePath()
                else points.toSmoothLinePath(defaultSmoothFactor)

        painter.prepare(color = lineColor, style = Paint.Style.STROKE, strokeWidth = lineThickness)
        canvas.drawPath(linePath, painter.paint)
    }

    override fun drawLineBackground(innerFrame: Frame, points: List<DataPoint>) {

        val linePath =
                if (!smooth) points.toLinePath()
                else points.toSmoothLinePath(defaultSmoothFactor)
        val backgroundPath = createBackgroundPath(linePath, points, innerFrame.bottom)

        if (fillColor != 0)
            painter.prepare(color = fillColor, style = Paint.Style.FILL)
        else
            painter.prepare(
                    shader = innerFrame.toLinearGradient(gradientFillColors),
                    style = Paint.Style.FILL
            )

        canvas.drawPath(backgroundPath, painter.paint)
    }

    override fun drawLabels(xLabels: List<Label>) {

        painter.prepare(
                textSize = labelsSize,
                color = labelsColor,
                font = labelsFont
        )
        xLabels.forEach {
            canvas.drawText(
                    it.label,
                    it.screenPositionX,
                    it.screenPositionY,
                    painter.paint
            )
        }
    }

    override fun drawPoints(points: List<DataPoint>) {
        if (pointsDrawableRes != -1) {
            points.forEach { dataPoint ->
                getDrawable(pointsDrawableRes)?.let {
                    it.centerAt(dataPoint.screenPositionX, dataPoint.screenPositionY)
                    it.draw(canvas)
                }
            }
        }
    }

    override fun drawDebugFrame(outerFrame: Frame, innerFrame: Frame, labelsFrame: List<Frame>) {
        painter.prepare(color = -0x1000000, style = Paint.Style.STROKE)
        canvas.drawRect(outerFrame.toRect(), painter.paint)
        canvas.drawRect(innerFrame.toRect(), painter.paint)
        labelsFrame.forEach { canvas.drawRect(it.toRect(), painter.paint) }
    }

    private fun createBackgroundPath(
            path: Path,
            points: List<DataPoint>,
            innerFrameBottom: Float
    ): Path {

        val res = Path(path)

        res.lineTo(points.last().screenPositionX, innerFrameBottom)
        res.lineTo(points.first().screenPositionX, innerFrameBottom)
        res.close()

        return res
    }

    private fun handleAttributes(typedArray: TypedArray) {
        typedArray.apply {
            lineColor = getColor(R.styleable.LineChartAttrs_chart_lineColor, lineColor)
            lineThickness =
                    getDimension(R.styleable.LineChartAttrs_chart_lineThickness, lineThickness)
            smooth = getBoolean(R.styleable.LineChartAttrs_chart_smoothLine, smooth)
            pointsDrawableRes =
                    getResourceId(R.styleable.LineChartAttrs_chart_pointsDrawable, pointsDrawableRes)
            recycle()
        }
    }

    companion object {
        private const val defaultSmoothFactor = 0.20f
        private const val defaultSmooth = false
        private const val defaultLineThickness = 4F
        private const val defaultFillColor = 0
        private const val defaultLineColor = Color.BLACK
    }
}
