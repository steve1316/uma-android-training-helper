package com.steve1316.uma_android_training_helper.data

class CharacterData {
	companion object {
		val characters = mapOf(
			"Tokai Teio" to mapOf(
				"\"女帝\"vs.\"帝王\"" to arrayListOf("Guts +10", "Skill points +30"),
				"叱られちゃった" to arrayListOf("Intelligence +10", "Power +5\nGuts +5"),
				"気づいちゃった！" to arrayListOf("Speed +5\nStamina +5", "Guts +5\nIntelligence +5", "Power +10"),
				"カラオケでパワーアップ！？" to arrayListOf("Guts +10", "Speed +10"),
				"わがままテイオーと思い出の景色" to arrayListOf("Guts +20", "Speed +10\nPower +10"),
				"ボクとマヤノとオトナの時間" to arrayListOf("Guts +10", "Intelligence +10"),
				"カイチョーみたいな勝負服" to arrayListOf("Speed +10\nIntelligence +10\n(random) Get Rising Star status", "Stamina +10\nGuts +10\n(random) Get Rising Star status"),
				"テイオーのジンクス" to arrayListOf("Stamina +20", "Intelligence +20"),
				"テイオーの武者修行" to arrayListOf("Guts +10\nSkill points +15", "抜け出し準備 hint +1"),
				"ボクとみんなとカップケーキ" to arrayListOf("Energy +5\nMotivation +1", "Speed +5\nPower +5"),
				"力のヒミツ！" to arrayListOf("Energy +10\nSkill points +5", "Energy +30\nSkill points +10\n(random) Speed -5\nGet Overweight status"),
				"褒められちゃった！" to arrayListOf("Stamina +10", "Speed +10"),
				"カイチョーとダジャレ" to arrayListOf("Stamina +5\nPower +5", "Speed +10"),
				"テイオー、ウマドルになる！？" to arrayListOf("Power +10", "Speed +10"),
				"ダンスレッスン" to arrayListOf("Stamina +10", "Power +10"),
				"新年の抱負" to arrayListOf("Guts +10", "Energy +20", "Skill points +20"),
			),
			"Daiwa Scarlet" to mapOf(
				"1番は見逃せない！" to arrayListOf("Energy +10\nSkill points +5", "Energy +30\nSkill points +10\n(random) Speed -5\nPower +5\nGet Overweight status"),
				"もうちょっとだけ" to arrayListOf("Skill points +30", "Power +10"),
				"楽しめ！1番！" to arrayListOf("Stamina +10\nSkill points +15", "負けん気 hint +1"),
				"優等生としては…" to arrayListOf("Intelligence +10", "Skill points +30"),
				"雨に降られて" to arrayListOf("Guts +10", "Intelligence +10"),
				"おススメのお店" to arrayListOf("Speed +5\nPower +5", "Guts +5\nMotivation +1"),
				"アイツの存在" to arrayListOf("Power +5", "Power +5\nNext race changed to Japanese Derby"),
				"一番星の下で" to arrayListOf("Skill points +30", "Speed +5\nStamina +5", "Power +10"),
				"最高のポーズ" to arrayListOf("Stamina +10\nPower +10", "Intelligence +20"),
				"休日の過ごし方" to arrayListOf("Energy +10", "Motivation +1\nIntelligence +5"),
				"先輩のアドバイス" to arrayListOf("Speed +10", "Power +10"),
				"あの服が似合う自分" to arrayListOf("Speed +20", "Guts +20"),
				"ついつい着たくなる！" to arrayListOf("Stamina +10\nIntelligence +10\n(random) Get Rising Star status", "Speed +10\nGuts +10\n(random) Get Rising Star status"),
				"ダンスレッスン" to arrayListOf("Guts +10", "Speed +10"),
				"新年の抱負" to arrayListOf("Intelligence +10", "Energy +20", "Skill points +20"),
			),
			"Rice Shower" to mapOf(
				"ライスにお任せ……！" to arrayListOf("Guts +10", "Speed +10"),
				"てんとう虫が離れても" to arrayListOf("Stamina +10", "Intelligence +10"),
				"甘くて真っ赤な1ページ" to arrayListOf("Energy +10\nSkill points +5", "Energy +30\nSkill points +10\n(random) Get Overweight status"),
				"雲が空を覆っても" to arrayListOf("Speed+5\nPower +5", "Guts +10"),
				"相応しい自分" to arrayListOf("Guts +10\nIntelligence +10\n(random) Get Charming status", "Stamina +10\nPower +10\n(random) Get Charming status"),
				"甘く賑やかな幸福" to arrayListOf("Speed +10\n Stamina +10", "Intelligence +20"),
				"わたしのたいよう" to arrayListOf("Guts +5\nIntelligence +5", "Speed +5\nPower+5", "Stamina +10"),
				"夕焼けの1ページ" to arrayListOf("Power +5\nGuts+ 5", "Stamina +5\nIntelligence +5"),
				"素敵な世界に会いたくて" to arrayListOf("Stamina +10", "Speed +5\nIntelligence +5"),
				"何事も前向きに？" to arrayListOf("Stamina +5\nGuts +10", "良バ場○ hint +1"),
				"相応しくない自分！" to arrayListOf("Guts +20", "Power +20"),
				"参考にしたくて" to arrayListOf("Energy -10\nGuts +20", "Energy +5\nSkill points+15"),
				"ダンスレッスン" to arrayListOf("Stamina +10", "Speed +10"),
				"新年の抱負" to arrayListOf("Guts +10", "Energy +20", "Skill points +20"),
			),
			
			// Shared, as in these options are all the exact same with the same results for all characters.
			"Shared" to mapOf(
				// "Failed Training"
				"お大事に！" to arrayListOf(
					"Motivation -1\nLast trained stat -5\n(random) Get Bad Practice status", "One of these will be selected at random\n----------\nMotivation -1\nLast trained stat -10\n" +
							"(random) Get Bad Practice status\n----------\nGet Good Practice ○ status"),
				
				// The other "Failed Training"
				"無茶は厳禁！" to arrayListOf(
					"Energy +10\nMotivation -1\nLast trained stat -10\n2 random stats -10\n(random) Get Bad Practice status", "One of these will be selected at random\n----------\nMotivation -1" +
							"\nLast trained stat -10\n2 random stats -10\nGet Bad Practice status\n----------\nEnergy +10\nGet Good Practice ○ status"),
				
				// "Extra Training"
				"追加の自主トレ" to arrayListOf("Energy -5\nLast trained stat +5\nYayoi Akikawa bond +5", "Energy +5"),
				
				// "First Summer"
				"夏合宿（2年目）にて" to arrayListOf("Power +10", "Guts +10"),
				
				// "Shrine visit"
				"初詣" to arrayListOf("Energy +30", "All stats +5", "Skill points +35"),
				
				// Events with Etsuko Otonashi, the President, and the weird Wrestler doctor lady.
				"愉快ッ！密着取材！" to arrayListOf("Stamina +10\nEtsuko Otonashi bond +5", "Guts +10\nEtsuko Otonashi bond +5"),
				"トレーナー並の知識" to arrayListOf("Power +10\nEtsuko Otonashi bond +5", "Speed +10\nEtsuko Otonashi bond +5"),
				"上々の面構えッ！" to arrayListOf("Energy -10\nPower +20\nGuts +20\nハヤテ一文字 hint +1", "Energy +30\nStamina +20\n好転一息 hint +1"),
				"あんし～ん笹針師、参☆上" to arrayListOf(
					"One of these will be selected at random\n----------\nAll stats +20\n----------\nEnergy -20\nAll stats -15\nMotivation -1\nGet Insomnia status",
					"One of these will be selected at random\n----------\nObtain コーナー回復○ skill\nObtain 直線回復 skill\n----------\nMotivation -1\nEnergy -20", "One of these will be selected at " +
							"random\n----------\nMaximum Energy +12\nEnergy +40\n----------\nMotivation -1\nEnergy -20\nGet Bad Practice status", "One of these will be selected at random" +
							"\n----------\nEnergy +20\nMotivation +1\nGet Charming ○ status\n----------\nEnergy -10/-20\nMotivation -1\n(random) Get Bad Practice status", "Energy +10")
			)
		)
	}
}
