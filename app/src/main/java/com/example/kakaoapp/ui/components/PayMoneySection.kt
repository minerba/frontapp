package com.example.kakaoapp.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kakaoapp.ui.theme.*

@Composable
fun PayMoneySection() {
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
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("페이머니", color = TextGray, fontSize = 13.sp)
                Spacer(modifier = Modifier.width(4.dp))
                Icon(Icons.Default.Info, contentDescription = null, tint = TextGrayDarker, modifier = Modifier.size(14.dp))
            }
            Row(horizontalArrangement = Arrangement.spacedBy(3.dp)) {
                Box(modifier = Modifier.size(6.dp).clip(CircleShape).background(TextGrayDark))
                Box(modifier = Modifier.size(6.dp).clip(CircleShape).background(CardDarker))
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("잔액 보기", color = TextGrayDark, fontSize = 22.sp)
                Icon(Icons.Default.ChevronRight, contentDescription = null, tint = TextGrayDarker, modifier = Modifier.size(16.dp))
            }
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedButton(
                    onClick = {},
                    shape = CircleShape,
                    border = BorderStroke(1.dp, BorderGray),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = TextWhite),
                    contentPadding = PaddingValues(horizontal = 20.dp, vertical = 6.dp)
                ) {
                    Text("충전", fontSize = 13.sp)
                }
                Button(
                    onClick = {},
                    shape = CircleShape,
                    colors = ButtonDefaults.buttonColors(containerColor = KakaoYellow, contentColor = Color.Black),
                    contentPadding = PaddingValues(horizontal = 20.dp, vertical = 6.dp)
                ) {
                    Text("송금", fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(CardDarker)
                .padding(horizontal = 16.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier.size(20.dp).clip(CircleShape).background(KakaoYellow),
                    contentAlignment = Alignment.Center
                ) {
                    Text("W", fontSize = 9.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                }
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    buildAnnotatedString {
                        withStyle(SpanStyle(color = KakaoYellow, fontWeight = FontWeight.SemiBold)) { append("+이자") }
                        withStyle(SpanStyle(color = TextWhite)) { append(" 혜택 받기") }
                    },
                    fontSize = 13.sp
                )
                Spacer(modifier = Modifier.width(6.dp))
                Box(
                    modifier = Modifier.size(12.dp).clip(CircleShape).background(KakaoYellow),
                    contentAlignment = Alignment.Center
                ) {
                    Text("p", fontSize = 7.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                }
                Spacer(modifier = Modifier.width(2.dp))
                Text("pay 쿠폰", color = TextGrayDark, fontSize = 10.sp)
            }
            Box(
                modifier = Modifier.size(24.dp).clip(CircleShape).background(CardDarkest),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null, tint = TextGrayDark, modifier = Modifier.size(14.dp))
            }
        }
    }
}
