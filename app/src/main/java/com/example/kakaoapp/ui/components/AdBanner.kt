package com.example.kakaoapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
fun AdBanner() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(CardDark)
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text("라이나생명THE건강한치아보험", color = TextGrayDark, fontSize = 11.sp)
            Spacer(modifier = Modifier.height(2.dp))
            Text("충전치료 몇번이든 보장", color = TextWhite, fontSize = 14.sp, fontWeight = FontWeight.Medium)
            Spacer(modifier = Modifier.height(4.dp))
            Text("가입 후 91일부터 보장, 충전치료 1년 이내 치료 시 50% 보장", color = TextGrayDarker, fontSize = 11.sp, lineHeight = 15.sp)
        }
        Spacer(modifier = Modifier.width(12.dp))
        Box {
            Text("🦷", fontSize = 36.sp)
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .clip(RoundedCornerShape(3.dp))
                    .background(Color(0xFF555555))
                    .padding(horizontal = 4.dp, vertical = 2.dp)
            ) {
                Text("AD", color = TextGray, fontSize = 9.sp, fontWeight = FontWeight.Medium)
            }
        }
    }
}
