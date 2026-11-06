package com.aniruddha.dreamsave.ui

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.cos
import kotlin.math.sin

private val DiwaliGold = Color(0xFFFFD36A)
private val DiwaliOrange = Color(0xFFFF8A32)

@Composable
fun DiwaliIntro(onContinue: () -> Unit) {
    val transition = rememberInfiniteTransition(label = "diwaliFireworks")
    val progress by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(animation = tween(2600), repeatMode = RepeatMode.Restart),
        label = "fireworkProgress"
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(Color(0xFF170510), Color(0xFF4B0715), Color(0xFF170A18))
                )
            )
    ) {
        Canvas(Modifier.fillMaxSize()) {
            val centers = listOf(
                Offset(size.width * 0.18f, size.height * 0.22f),
                Offset(size.width * 0.82f, size.height * 0.30f),
                Offset(size.width * 0.52f, size.height * 0.13f)
            )
            val colors = listOf(DiwaliGold, DiwaliOrange, Color(0xFFFFDCE8))
            centers.forEachIndexed { index, center ->
                val phase = (progress + index * 0.29f) % 1f
                val radius = (22f + phase * 112f) * (size.width / 400f).coerceAtLeast(0.7f)
                val alpha = (1f - phase) * 0.9f
                for (ray in 0 until 18) {
                    val angle = (ray * 20f + index * 11f) * (Math.PI / 180.0)
                    val dx = cos(angle).toFloat()
                    val dy = sin(angle).toFloat()
                    drawLine(
                        color = colors[index].copy(alpha = alpha),
                        start = center + Offset(dx * radius * 0.25f, dy * radius * 0.25f),
                        end = center + Offset(dx * radius, dy * radius),
                        strokeWidth = 2.2f,
                        cap = StrokeCap.Round
                    )
                    drawCircle(
                        color = colors[index].copy(alpha = alpha),
                        radius = 2.5f * (1f - phase * 0.45f),
                        center = center + Offset(dx * radius, dy * radius)
                    )
                }
                drawCircle(colors[index].copy(alpha = alpha * 0.25f), radius * 0.18f, center)
            }
            // Tiny, steady stars between the animated bursts.
            for (i in 0 until 42) {
                val x = (i * 73f % size.width)
                val y = (i * 131f % size.height)
                drawCircle(Color(0xFFFFE8B0).copy(alpha = 0.18f + (i % 3) * 0.08f), 1.5f, Offset(x, y))
            }
        }

        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth()
                .padding(horizontal = 28.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("🪔  ✨  🪔", fontSize = 34.sp)
            Spacer(Modifier.height(22.dp))
            Text(
                "ANI STUDIO PRESENTS",
                color = DiwaliGold,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 3.sp,
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.height(16.dp))
            Text(
                "DREAMSAVE",
                color = Color.White,
                fontSize = 42.sp,
                fontWeight = FontWeight.Black,
                letterSpacing = 2.sp,
                textAlign = TextAlign.Center
            )
            Text(
                "DIWALI UPDATE",
                color = DiwaliGold,
                fontSize = 19.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 4.sp,
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.height(28.dp))
            Text(
                "WISHING YOU",
                color = Color.White.copy(alpha = 0.88f),
                fontSize = 14.sp,
                letterSpacing = 3.sp
            )
            Text(
                "HAPPY DIWALI!",
                color = DiwaliGold,
                fontSize = 29.sp,
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.height(14.dp))
            Text(
                "May your dreams shine brighter and your savings grow stronger.",
                color = Color.White.copy(alpha = 0.78f),
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
                lineHeight = 21.sp
            )
            Spacer(Modifier.height(36.dp))
            Button(
                onClick = onContinue,
                shape = RoundedCornerShape(28.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = DiwaliGold,
                    contentColor = Color(0xFF3A0711)
                ),
                modifier = Modifier.fillMaxWidth(0.76f).height(54.dp)
            ) {
                Text("ENTER DREAMSAVE  →", fontWeight = FontWeight.ExtraBold, letterSpacing = 1.sp)
            }
            Spacer(Modifier.height(18.dp))
            Text(
                "A FESTIVE EDITION BY ANI STUDIO",
                color = Color.White.copy(alpha = 0.56f),
                fontSize = 10.sp,
                letterSpacing = 2.sp
            )
        }

        Text(
            "✦  🪔  ✦",
            modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 30.dp),
            color = DiwaliGold,
            fontSize = 24.sp
        )
    }
}
