package com.steve1316.uma_android_training_helper.data

class SupportData {
	companion object {
		val R = mapOf(
			"El Condor Pasa" to mapOf(
				"シークレット・ノート" to arrayListOf("Power +10\nEl Condor Pasa bond +5", "晴れの日○ hint +1\nEl Condor Pasa bond +5"),
				"メラメラ・ファイアー！" to arrayListOf("Stamina +10\nEl Condor Pasa bond +5", "Energy -10\nPower +20\nEl Condor Pasa bond +5")
			),
			"Kitasan Black" to mapOf(
				"あぁ、故郷" to arrayListOf("Speed +5\nPower +10\nKitasan Black bond +5", "Get Good Practice ○ status\nKitasan Black bond +5"),
				"あぁ、友情" to arrayListOf("Motivation +1\nPower +5\nKitasan Black bond +5", "Energy +10\nKitasan Black bond +5")
			),
			"Mejiro Dober" to mapOf(
				"喜んでくれるかな……" to arrayListOf("Skill points +45\nMejiro Dober bond +5", "負けん気 hint +1\nMejiro Dober bond +5"),
				"やってみてもいい" to arrayListOf("Energy +15\nMejiro Dober bond +5", "Motivation +1\nSkill points +15\nMejiro Dober bond +5")
			),
			"Silence Suzuka" to mapOf(
				"どこまでも" to arrayListOf("Speed +10\nStamina +5\nSilence Suzuka bond +5", "Speed +15\nSilence Suzuka bond +5"),
				"どうすれば" to arrayListOf(
					"Speed +5\nStamina +5\nIntelligence +5\nSilence Suzuka bond +5", "One of these will be selected at random\n----------\nSpeed +15\nSilence Suzuka bond +5\n----------\n" +
							"左回り○ hint +1\nSilence Suzuka bond +5")
			),
			"Twin Turbo" to mapOf(
				"思い込んだら一直線！" to arrayListOf("Motivation -1\nSpeed +20", "Energy -10\nPower +20"),
				"燃えてきた！！" to arrayListOf("Energy +15\nTwin Turbo bond +5", "先駆け hint +1\nTwin Turbo bond +5")
			),
			"Yaeno Muteki" to mapOf(
				"剛毅朴訥、仁に近し" to arrayListOf("Speed +10"),
				"嗚呼、守りたい……！" to arrayListOf("Stamina +10\nPower +10", "中距離コーナー○ hint +1")
			)
		)
		
		val SR = mapOf(
			"Mejiro Dober" to mapOf(
				"褒められたって" to arrayListOf("Energy +10\nGuts +5"),
				"見られてたって" to arrayListOf("Energy +5/+20\nStamina +5/+15\n差し切り体勢 hint +1")
			),
			"Zenno Rob Roy" to mapOf(
				"読書少女と魔法少女？" to arrayListOf("Stamina +5\nIntelligence +5\nZenno Rob Roy bond +5", "Energy +20\nPower +10\nZenno Rob Roy bond +5\nEvent chain ended"),
				"いつか、『物語』の主役に" to arrayListOf("Stamina +10\nIntelligence +10\n鋭い眼光 hint +1"),
				"託された物語" to arrayListOf("Stamina +10\nIntelligence +10\nZenno Rob Roy bond +5", "中距離直線○ hint +1\nZenno Rob Roy bond +5"),
				"読書家あるある" to arrayListOf("Speed +5\nIntelligence +5\nZenno Rob Roy bond +5", "Energy +10\nPower +5\nZenno Rob Roy bond +5")
			)
		)
		
		val SSR = mapOf(
			"El Condor Pasa" to mapOf(
				"世界級の……！？" to arrayListOf("Power +5\nGuts +5"),
				"ダシが重要！！" to arrayListOf("Energy +30\nEl Condor Pasa bond +5", "スタミナキープ hint +1\nEl Condor Pasa bond +5"),
				"ルチャドーラ・エル！！" to arrayListOf("Power +10\nキラーチューン hint +1"),
				"シークレット・ノート" to arrayListOf("Power +10\nEl Condor Pasa bond +5", "晴れの日○ hint +1\nEl Condor Pasa bond +5"),
				"メラメラ・ファイアー！" to arrayListOf("Stamina +10\nEl Condor Pasa bond +5", "Energy -10\nPower +20\nEl Condor Pasa bond +5")
			),
			"Kitasan Black" to mapOf(
				"輝き追いかけて" to arrayListOf("Motivation +1\nSpeed +2\nPower +2\nIntelligence +2\nKitasan Black bond +5"),
				"情けは人のためならず" to arrayListOf("Energy +10\nMotivation +1\nKitasan Black bond +5", "Speed +5/+10\n直線巧者 hint +1/+3\nKitasan Black bond +5"),
				"2人で行くこの花道" to arrayListOf(
					"One of these will be selected at random\n----------\nSpeed +5\nPower +5\nKitasan Black bond +5\n弧線のプロフェッサー hint +1\n----------\nSpeed +10\nPower +10\n" +
							"Kitasan Black bond +5\n弧線のプロフェッサー hint +3"),
				"あぁ、故郷" to arrayListOf("Speed +5\nPower +10\nKitasan Black bond +5", "Get Good Practice ○ status\nKitasan Black bond +5"),
				"あぁ、友情" to arrayListOf("Motivation +1\nPower +5\nKitasan Black bond +5", "Energy +10\nKitasan Black bond +5")
			),
			"Mejiro Dober" to mapOf(
				"踏み出す、一歩" to arrayListOf("Energy -10\nSkill points +15~45\nMejiro Dober bond +5", "Guts +5\nIntelligence +5\nMejiro Dober bond +5"),
				"高め合い、二歩" to arrayListOf("Guts +3\nIntelligence +3\nSkill points +5"),
				"三歩進み、二歩下がって、一歩" to arrayListOf("Energy -10\nGuts +5\nIntelligence +5\n大局観 hint +1"),
				"やってみてもいい" to arrayListOf("Energy +15\nMejiro Dober bond +5", "Motivation +1\nSkill points +15\nMejiro Dober bond +5"),
				"喜んでくれるかな……" to arrayListOf("Skill points +45\nMejiro Dober bond +5", "負けん気 hint +1\nMejiro Dober bond +5")
			),
			"Silence Suzuka" to mapOf(
				"手紙→？" to arrayListOf("Speed +10"),
				"手紙→皆の気持ち→？" to arrayListOf("Speed +10\nPower +10"),
				"手紙→皆の気持ち→ちゃんと待っててね" to arrayListOf("Speed +10\n逃亡者 hint +1"),
				"どこまでも" to arrayListOf("Speed +10\nStamina +5\nSilence Suzuka bond +5", "Speed +15\nSilence Suzuka bond +5"),
				"どうすれば" to arrayListOf(
					"Speed +5\nStamina +5\nIntelligence +5\nSilence Suzuka bond +5", "One of these will be selected at random\n----------\nSpeed +15\nSilence Suzuka bond +5\n----------\n" +
							"左回り○ hint +1\nSilence Suzuka bond +5")
			),
			"Twin Turbo" to mapOf(
				"怖くないもん！" to arrayListOf(
					"One of these will be selected at random\n----------\nSpeed +10\nTwin Turbo bond +5\n----------\nEnergy -10\nSpeed +10\nEvent chain ended", "Energy +20\nEvent chain ended"),
				"捕まらないもん！" to arrayListOf(
					"One of these will be selected at random\n----------\nSpeed +15\n(random) 先頭プライド hint +1/+3\n----------\nEnergy -10\nSpeed +10\n(random) 先頭プライド hint +1/+3\nEvent " +
							"chain ended", "Energy +25\nEvent chain ended"),
				"ターボは強いんだもん！" to arrayListOf(
					"One of these will be selected at random\n----------\nEnergy -10\nSpeed +25\n先手必勝 hint +1/+3\n先駆け hint +1/+3\n----------\nEnergy -10\nSpeed +5\n先手必勝 hint +1/+3\n" +
							"先手必勝 hint +1/+3", "Energy +15\n展開窺い hint +1"),
				"思い込んだら一直線！" to arrayListOf("Motivation -1\nSpeed +20", "Energy -10\nPower +20"),
				"燃えてきた！！" to arrayListOf("Energy +15\nTwin Turbo bond +5", "先駆け hint +1\nTwin Turbo bond +5")
			),
			"Yaeno Muteki" to mapOf(
				"電脳奥義炸裂！ヤエノ出稽古修行" to arrayListOf("Speed +5\nPower +5\nSkill points +10"),
				"理の食VS暴の食" to arrayListOf("Energy +10\nSkill points +10\nYaeno Muteki bond +5", "Energy +10\nIntelligence +10\nSkill points +10\n遊びはおしまいっ！hint +3\nEvent chain ended"),
				"ヤエノムテキ恋歌地獄変" to arrayListOf("Energy -10\nSpeed +5\n(random) Stamina +5\n(random) Power +10\nアガッてきた！hint +1/+3"),
				"剛毅朴訥、仁に近し" to arrayListOf("Speed +10", "Motivation +1\nPower +5"),
				"嗚呼、守りたい……！" to arrayListOf("Stamina +10\nPower +10", "中距離コーナー○ hint +1")
			)
		)
	}
}