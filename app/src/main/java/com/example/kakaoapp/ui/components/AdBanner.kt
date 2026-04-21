package com.example.kakaoapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
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
            Text("라이나생명", color = TextGrayDark, fontSize = 11.sp)
            Spacer(modifier = Modifier.height(2.dp))
            Text("암보험료 월 2만원대,\n라이나생명 암보험", color = TextWhite, fontSize = 14.sp, fontWeight = FontWeight.Medium, lineHeight = 20.sp)
            Spacer(modifier = Modifier.height(4.dp))
            Text("최대 5천만원 보장, 지금 바로 확인하세요", color = TextGrayDarker, fontSize = 11.sp, lineHeight = 15.sp)
        }
        Spacer(modifier = Modifier.width(12.dp))
        Box {
            Box(
                modifier = Modifier
                    .size(64.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color(0xFF003087)),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("LINA", color = TextWhite, fontSize = 8.sp, fontWeight = FontWeight.Bold, letterSpacing = 2.sp)
                    HorizontalDivider(modifier = Modifier.width(32.dp).padding(vertical = 2.dp), color = Color(0x66FFFFFF), thickness = 1.dp)
                    Text("생명보험", color = TextWhite, fontSize = 7.sp)
                }
            }
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
