package com.example.kakaoapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kakaoapp.ui.theme.*

@Composable
fun CreditScoreSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(CardDark)
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("신용점수", color = TextWhite, fontSize = 14.sp, fontWeight = FontWeight.Medium)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("17시간 전", color = TextGrayDark, fontSize = 12.sp)
                Icon(Icons.Default.ChevronRight, contentDescription = null, tint = TextGrayDark, modifier = Modifier.size(14.dp))
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            // KCB
            Box(
                modifier = Modifier.weight(1f).clip(RoundedCornerShape(12.dp)).background(CardDarker).padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text("점수 보기", color = TextGrayDark, fontSize = 14.sp)
                    Text("KCB", color = TextGrayDark, fontSize = 12.sp)
                }
            }

            // NICE
            Box(
                modifier = Modifier.weight(1f).clip(RoundedCornerShape(12.dp)).background(CardDarker).padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text("점수 보기", color = TextGrayDark, fontSize = 14.sp)
                    Text("NICE", color = TextGrayDark, fontSize = 12.sp)
                }
            }
        }
    }
}
