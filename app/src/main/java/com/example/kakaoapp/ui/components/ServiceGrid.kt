package com.example.kakaoapp.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kakaoapp.ui.theme.*

@Composable
fun ServiceGrid(onCreditClick: () -> Unit = {}) {
    val services = listOf(
        Triple("결제", Color(0xFF2A2A2A), @Composable { BarcodeIconView() }),
        Triple("신용관리", KakaoYellow, @Composable { FaceIconView() }),
        Triple("대출쿠폰", Color(0xFF3D5AFE), @Composable { CouponIconView() }),
        Triple("카드발급", Color(0xFF4A4A4A), @Composable { CardIconView() }),
        Triple("내 카드값", Color(0xFF555555), @Composable { CardBillIconView() }),
        Triple("비상금대출", Color(0xFF1DB954), @Composable { EmergencyIconView() }),
        Triple("손해보험", Color(0xFF2A2A2A), @Composable { InsuranceIconView() }),
        Triple("더보기", Color(0xFF2A2A2A), @Composable { MoreIconView() }),
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(CardDark)
            .padding(20.dp)
    ) {
        services.chunked(4).forEachIndexed { rowIndex, rowItems ->
            if (rowIndex > 0) Spacer(modifier = Modifier.height(20.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
                rowItems.forEach { (label, _, icon) ->
                    val onClick = if (label == "신용관리") onCreditClick else ({})
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .width(64.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .clickable { onClick() }
                            .padding(4.dp)
                    ) {
                        Box(
                            modifier = Modifier.size(52.dp).clip(RoundedCornerShape(14.dp)).background(Color.Transparent),
                            contentAlignment = Alignment.Center
                        ) { icon() }
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(label, color = TextGray, fontSize = 11.sp, textAlign = TextAlign.Center, lineHeight = 15.sp)
                    }
                }
            }
        }
    }
}

@Composable
private fun BarcodeIconView() {
    Box(modifier = Modifier.size(52.dp).clip(RoundedCornerShape(14.dp)).background(CardDarker), contentAlignment = Alignment.Center) {
        Row(horizontalArrangement = Arrangement.spacedBy(2.dp), verticalAlignment = Alignment.CenterVertically) {
            listOf(3, 1, 4, 1, 2, 1, 4).forEach { w ->
                Box(modifier = Modifier.width(w.dp).height(22.dp).background(Color(0xFF888888)))
            }
        }
    }
}

@Composable
private fun FaceIconView() {
    Box(modifier = Modifier.size(52.dp).clip(CircleShape).background(KakaoYellow), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Box(modifier = Modifier.size(4.dp, 5.dp).clip(RoundedCornerShape(2.dp)).background(Color(0xFF3C1E1E)))
                Box(modifier = Modifier.size(4.dp, 5.dp).clip(RoundedCornerShape(2.dp)).background(Color(0xFF3C1E1E)))
            }
            Spacer(modifier = Modifier.height(4.dp))
            Box(modifier = Modifier.size(16.dp, 4.dp).clip(RoundedCornerShape(2.dp)).background(Color(0xFF3C1E1E)))
        }
    }
}

@Composable
private fun CouponIconView() {
    Box(modifier = Modifier.size(52.dp).clip(RoundedCornerShape(14.dp)).background(Color(0xFF3D5AFE)), contentAlignment = Alignment.Center) {
        Box(
            modifier = Modifier.size(36.dp, 22.dp).clip(RoundedCornerShape(4.dp))
                .border(1.dp, Color.White.copy(alpha = 0.5f), RoundedCornerShape(4.dp)),
            contentAlignment = Alignment.Center
        ) {
            Box(modifier = Modifier.size(14.dp).clip(CircleShape).background(KakaoYellow), contentAlignment = Alignment.Center) {
                Text("pay", fontSize = 4.sp, fontWeight = FontWeight.Bold, color = Color.Black)
            }
        }
    }
}

@Composable
private fun CardIconView() {
    Box(modifier = Modifier.size(52.dp).clip(RoundedCornerShape(14.dp)).background(Color(0xFF4A4A4A)), contentAlignment = Alignment.Center) {
        Box(modifier = Modifier.size(36.dp, 22.dp).clip(RoundedCornerShape(4.dp)).background(Color(0xFF555555))) {
            Box(modifier = Modifier.fillMaxWidth().height(7.dp).background(Color(0xFF666666)).align(Alignment.Center))
        }
    }
}

@Composable
private fun CardBillIconView() {
    Box(modifier = Modifier.size(52.dp).clip(RoundedCornerShape(14.dp)).background(Color(0xFF555555)), contentAlignment = Alignment.Center) {
        Box(contentAlignment = Alignment.BottomEnd) {
            Box(modifier = Modifier.size(32.dp, 20.dp).clip(RoundedCornerShape(4.dp)).background(Color(0xFF444444)))
            Box(modifier = Modifier.size(16.dp).clip(CircleShape).background(KakaoYellow), contentAlignment = Alignment.Center) {
                Text("W", fontSize = 8.sp, fontWeight = FontWeight.Bold, color = Color.Black)
            }
        }
    }
}

@Composable
private fun EmergencyIconView() {
    Box(modifier = Modifier.size(52.dp).clip(CircleShape).background(Color(0xFF1DB954)), contentAlignment = Alignment.Center) {
        Text("W", color = TextWhite, fontSize = 22.sp, fontWeight = FontWeight.Bold)
    }
}

@Composable
private fun InsuranceIconView() {
    Box(modifier = Modifier.size(52.dp).clip(RoundedCornerShape(14.dp)).background(CardDarker), contentAlignment = Alignment.Center) {
        Box(modifier = Modifier.size(28.dp).clip(CircleShape).background(KakaoYellow), contentAlignment = Alignment.Center) {
            Text("pay", fontSize = 7.sp, fontWeight = FontWeight.Bold, color = Color.Black)
        }
    }
}

@Composable
private fun MoreIconView() {
    Box(modifier = Modifier.size(52.dp).clip(CircleShape).background(CardDarker), contentAlignment = Alignment.Center) {
        Text("∨", color = TextGray, fontSize = 16.sp, fontWeight = FontWeight.Bold)
    }
}
