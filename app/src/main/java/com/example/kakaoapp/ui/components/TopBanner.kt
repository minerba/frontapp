package com.example.kakaoapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kakaoapp.ui.theme.*

@Composable
fun TopBannerCard() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(CardDark)
            .padding(20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = "고유가 지원금 계산기로\n예상 수령액 알아봐요",
                color = TextWhite,
                fontSize = 15.sp,
                lineHeight = 22.sp,
                modifier = Modifier.padding(bottom = 12.dp)
            )
            Button(
                onClick = {},
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(
                    containerColor = KakaoYellow,
                    contentColor = Color.Black
                ),
                contentPadding = PaddingValues(horizontal = 20.dp, vertical = 8.dp)
            ) {
                Text("미리 보기", fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
            }
        }
        Spacer(modifier = Modifier.width(12.dp))
        CalculatorIllustration()
    }
}

@Composable
private fun CalculatorIllustration() {
    Box(
        modifier = Modifier
            .size(68.dp, 76.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(CardDarker),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Box(
                modifier = Modifier
                    .size(28.dp)
                    .clip(CircleShape)
                    .background(KakaoYellow),
                contentAlignment = Alignment.Center
            ) {
                Text("pay", fontSize = 7.sp, fontWeight = FontWeight.Bold, color = Color.Black)
            }
            Spacer(modifier = Modifier.height(6.dp))
            Text("100", color = TextWhite, fontSize = 12.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(4.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(3.dp)) {
                repeat(3) { i ->
                    Box(
                        modifier = Modifier
                            .size(10.dp, 8.dp)
                            .clip(RoundedCornerShape(2.dp))
                            .background(if (i == 2) KakaoYellow else Color(0xFF444444))
                    )
                }
            }
        }
    }
}
