package ru.otus.compose.customlayout

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CustomLayoutHW(
    columns: Int,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit = { }
) {
    Layout(
        modifier = modifier,
        content = content
    ) { measurables, constraints ->
        if (columns <= 0 || measurables.isEmpty()) {
            return@Layout layout(0, 0) {}
        }

        // Измеряем все элементы
        val placeables = measurables.map { measurable ->
            measurable.measure(constraints)
        }

        // Вычисляем отступы в пикселях
        val horizontalSpacing = 8.dp.roundToPx()
        val verticalSpacing = 8.dp.roundToPx()

        // Группируем по строкам
        val rows = placeables.chunked(columns)

        // Вычисляем высоту каждой строки (максимальная высота в строке)
        val rowHeights = rows.map { row ->
            row.maxOfOrNull { it.height } ?: 0
        }

        // Находим максимальную ширину элемента в каждой колонке
        val columnWidths = mutableListOf<Int>()
        for (col in 0 until columns) {
            var maxWidth = 0
            for (row in rows) {
                if (col < row.size) {
                    maxWidth = maxOf(maxWidth, row[col].width)
                }
            }
            columnWidths.add(maxWidth)
        }

        // Общая ширина макета
        val totalWidth = columnWidths.sum() + (columns - 1) * horizontalSpacing

        // Общая высота с учетом отступов между строками
        val totalHeight = rowHeights.sum() + (rows.size - 1) * verticalSpacing

        layout(totalWidth, totalHeight) {
            var yPosition = 0

            rows.forEachIndexed { rowIndex, row ->
                val rowHeight = rowHeights[rowIndex]
                var xPosition = 0

                row.forEachIndexed { index, placeable ->
                    val columnWidth = columnWidths[index]

                    // Выравниваем по центру колонки по горизонтали
                    val xOffset = xPosition + (columnWidth - placeable.width) / 2

                    // Выравниваем по центру строки по вертикали
                    val yOffset = yPosition + (rowHeight - placeable.height) / 2

                    placeable.placeRelative(
                        x = xOffset,
                        y = yOffset
                    )

                    xPosition += columnWidth + horizontalSpacing
                }

                // Перемещаемся на следующую строку с отступом
                yPosition += rowHeight + verticalSpacing
            }
        }
    }
}

@Preview
@Composable
fun CustomLayoutHWPreview() {
    Surface {
        CustomLayoutHW(
            columns = 3,
            modifier = Modifier
                .padding(4.dp)
                .border(2.dp, color = Color.Black)
                .padding(4.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.catanddot),
                contentDescription = null,
                Modifier
                    .size(100.dp)
                    .padding(4.dp)
                    .border(2.dp, color = Color.Black)
            )
            Image(
                painter = painterResource(id = R.drawable.catanddot),
                contentDescription = null,
                Modifier
                    .size(110.dp)
                    .padding(4.dp)
                    .border(2.dp, color = Color.Black)
            )
            Image(
                painter = painterResource(id = R.drawable.catanddot),
                contentDescription = null,
                Modifier
                    .size(90.dp)
                    .padding(4.dp)
                    .border(2.dp, color = Color.Black)
            )
            Image(
                painter = painterResource(id = R.drawable.catanddot),
                contentDescription = null,
                Modifier
                    .size(120.dp)
                    .padding(4.dp)
                    .border(2.dp, color = Color.Black)
            )
            Image(
                painter = painterResource(id = R.drawable.catanddot),
                contentDescription = null,
                Modifier
                    .size(100.dp)
                    .padding(4.dp)
                    .border(2.dp, color = Color.Black)
            )
            Image(
                painter = painterResource(id = R.drawable.catanddot),
                contentDescription = null,
                Modifier
                    .size(80.dp)
                    .padding(4.dp)
                    .border(2.dp, color = Color.Black)
            )
            Image(
                painter = painterResource(id = R.drawable.catanddot),
                contentDescription = null,
                Modifier
                    .size(100.dp)
                    .padding(4.dp)
                    .border(2.dp, color = Color.Black)
            )
            Image(
                painter = painterResource(id = R.drawable.catanddot),
                contentDescription = null,
                Modifier
                    .size(120.dp)
                    .padding(4.dp)
                    .border(2.dp, color = Color.Black)
            )
            Image(
                painter = painterResource(id = R.drawable.catanddot),
                contentDescription = null,
                Modifier
                    .size(100.dp)
                    .padding(4.dp)
                    .border(2.dp, color = Color.Black)
            )
            Image(
                painter = painterResource(id = R.drawable.catanddot),
                contentDescription = null,
                Modifier
                    .size(90.dp)
                    .padding(4.dp)
                    .border(2.dp, color = Color.Black)
            )
        }
    }
}