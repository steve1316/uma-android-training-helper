package com.steve1316.uma_android_training_helper.data

class SupportData {
	companion object {
		// Note that different Support Cards of different rarities of the same Character share the same events except for their 3-part progressing events.
		// Their secondary names that differentiate between each Support card of the same Character is from the GameWith website, https://gamewith.jp/uma-musume/article/show/255037
		val supports = mapOf(
			"Agnes Digital" to mapOf(
				// R Power トレセン学園
				"ウマ娘ちゃん欠乏症" to arrayListOf("Energy +5\nSpeed +5\nAgnes Digital bond +5", "Speed +5\nPower +5\nAgnes Digital bond +5"),
				"エモのためなら雨の中でも" to arrayListOf("雨の日○ hint +1\nAgnes Digital bond +5", "道悪○ hint +1\nAgnes Digital bond +5"),
				
				// SR Power デジタル充電中＋
				"幸福のヲタライフ" to arrayListOf("Power +10"),
				"ウマ娘ちゃんへの愛は永遠です！" to arrayListOf("Speed +5\nPower +10\n追込駆け引き hint +1"),
			),
			"Agnes Tachyon" to mapOf(
				// R Intelligence トレセン学園
				"要検証・睡眠時間と作業能率" to arrayListOf("Power +5\nIntelligence +5\nAgnes Tachyon bond +5", "Intelligence +10"),
				"要検証・他者の介在による偶発性" to arrayListOf("差しのコツ○ hint +1\nAgnes Tachyon bond +5", "Intelligence +10\nAgnes Tachyon bond +5"),
				
				// SR Intelligence 生体Aに関する実験的研究
				"Report『特化鍛錬による可能性』" to arrayListOf("Intelligence +5\nSkill points +15"),
				"Report『未定（休憩も大事）』" to arrayListOf("Intelligence +10\nSkill points +15\n逃げけん制 hint +1"),
			),
			"Air Groove" to mapOf(
				// R Guts トレセン学園
				"俊敏にして剛腕" to arrayListOf("Power +15\nAir Groove bond +5", "Speed +10\nStamina +5\nAir Groove bond +5"),
				"峻厳にして優渥" to arrayListOf("臨機応変 hint +1\nAir Groove bond +5", "Energy +10\nIntelligence +10"),
				
				// SR Guts 副会長の一刺し
				"凛と胡蝶蘭" to arrayListOf("Power +5\nGuts +5"),
				"樫に咲け" to arrayListOf("Energy -20\nPower +5\nGuts +5\n直線加速 hint +1\nAir Groove bond +5"),
			),
			"Air Shakur" to mapOf(
				// R Intelligence トレセン学園
				"//要検証" to arrayListOf("Energy +10\nGuts +5\nAir Shakur bond +5", "Energy -10\nStamina +5\nGuts +10\nAir Shakur bond +5"),
				"//絶対欲しい" to arrayListOf("ペースキープ hint +1\nAir Shakur bond +5", "Maximum Energy +4\nGuts +5\nAir Shakur bond +5"),
				
				// SSR Intelligence 7センチの先へ
				"ノイズは不要" to arrayListOf("Stamina +5\nIntelligence +5"),
				"リアリストの情熱" to arrayListOf("Stamina +5\nIntelligence +5\nSkill points +5"),
				"オレが最強" to arrayListOf("Stamina +5\nIntelligence +10\n勝利への執念 hint +1")
			),
			"Aoi Kiryuin" to mapOf(
				// R Friendship トレセン学園
				"お疲れ様です！" to arrayListOf("Intelligence +3\nAoi Kiryuin bond +5"),
				"トレーナー心得『指導は常に改良せよ』" to arrayListOf("Energy +10\nSkill points +15\nAoi Kiryuin bond +5", "Speed +5\nIntelligence +5\nAoi Kiryuin bond +5"),
				"趣味を探して" to arrayListOf("Energy +20\nSkill points +15\nMotivation +1\nAoi Kiryuin bond +5\nCan start dating", "Motivation -1\nおひとり様○ hint +1\nAoi Kiryuin bond -5\nEvent chain ended"),
				
				// SR Friendship 共に同じ道を！
				"身をもって学ぶべし！" to arrayListOf("Energy +25\nSkill points +30\nMotivation +2\nAoi Kiryuin bond +5"),
				"公園での遊び方" to arrayListOf(
					"Energy +25\nIntelligence +5\nAoi Kiryuin bond +5\n(random) Motivation +1",
					"One of these will be selected at random\n----------\nSkill points +15\nAoi Kiryuin bond +5\n----------\nSpeed +5\nSkill points +45\nMotivation +1\nAoi Kiryuin bond +5"),
				"暴走トレーニング" to arrayListOf("Energy +20\nSkill points +30\nMotivation +1\nAoi Kiryuin bond +5"),
				"尊重という幸福" to arrayListOf("Power +10\nSkill points +45\nMotivation +1\nAoi Kiryuin bond +5"),
				"夢を追い続ける限り" to arrayListOf(
					"One of these will be selected at random\n----------\nStamina +5\nSkill points +15\nAoi Kiryuin bond +5\n逃げけん制 hint +1\n----------\nStamina +5\nPower +5\nGuts +5\n" +
							"Skill points +45\nhint +3\nAoi Kiryuin bond +5"),
			),
			"Biko Pegasus" to mapOf(
				// R Speed トレセン学園
				"ヒーローの苦悩" to arrayListOf("Energy +15\nBiko Pegasus bond +5", "Energy +5\nPower +5\nBiko Pegasus bond +5"),
				"必殺技を手に入れろ！" to arrayListOf("短距離直線○ hint +1\nBiko Pegasus bond +5", "Energy +30\nBiko Pegasus bond +5"),
				
				// SSR Speed 必殺！Wキャロットパンチ
				"熱い誓い！ アタシはヒーローになる！" to arrayListOf("Speed +5\nGuts +5"),
				"猛特訓！ 現れたキャロットマン！？" to arrayListOf("Speed +5\nGuts +5"),
				"決戦！ 栄光の勝利をこの手に！" to arrayListOf("Speed +5\nPower +5\nGuts +5\nプランX hint +1"),
			),
			"Biwa Hayahide" to mapOf(
				// R Power トレセン学園
				"脱・無難論" to arrayListOf("Energy -10\n内弁慶 hint +1\nBiwa Hayahide bond +5", "Energy +10\nStamina +10\nBiwa Hayahide bond +5"),
				"ぎりぎり様相論" to arrayListOf("Power +15\nBiwa Hayahide bond +5", "Speed +10\nSkill points +15\nBiwa Hayahide bond +5"),
				
				// SR Power 検証、開始
				"世話焼きお姉ちゃん" to arrayListOf("Energy -10", "Power +15"),
				"トレーニングは理論的に" to arrayListOf(
					"One of these will be selected at random\n----------\nEnergy -20\nPower +5\n追い上げ hint +1\n----------\nEnergy -10\nStamina +5\nPower +15\nBiwa Hayahide bond +5\n追い上げ hint +3"),
			),
			"Daitaku Helios" to mapOf(
				// R Power トレセン学園
				"太陽とエンカウント☆" to arrayListOf("Power +10\nDaitaku Helios bond +5", "Get Rising Star status\nDaitaku Helios bond +5"),
				"笑顔フォーエバー" to arrayListOf("Speed +5\nPower +10\nDaitaku Helios bond +5", "伏兵○ hint +1\nDaitaku Helios bond +5"),
				
				// SR Power パリピ・ぱーりないと！
				"#bff #Party!" to arrayListOf("Power +10\nDaitaku Helios bond +5", "Speed +10\nDaitaku Helios bond +5"),
				"#lol #Party! #2nd" to arrayListOf("Speed +10\nPower +10\nhint +3\nDaitaku Helios bond +5", "Energy +20\n展開窺い hint +1\nDaitaku Helios bond +5"),
			),
			"Daiwa Scarlet" to mapOf(
				// R Intelligence トレセン学園
				"明日は私が勝つんだから！" to arrayListOf("Intelligence +10\nDaiwa Scarlet bond +5", "Motivation +1\nSkill points +15\nDaiwa Scarlet bond +5"),
				"このくらい平気なんだから！" to arrayListOf("hint +1\nDaiwa Scarlet bond +5", "Energy +20\nMotivation +1\nDaiwa Scarlet bond +5"),
				
				// SR Intelligence 努力は裏切らない！
				"相手が誰でも負けないんだから！" to arrayListOf("Energy +15\nIntelligence +5\nDaiwa Scarlet bond +5"),
				"清掃だって負けないんだから！" to arrayListOf("Intelligence +10\nまき直し hint +1"),
			),
			"Eishin Flash" to mapOf(
				// R Speed トレセン学園
				"想定外への対応" to arrayListOf("Guts +10\nEishin Flash bond +5", "徹底マーク○ hint +1\nEishin Flash bond +5"),
				"想定外のお昼" to arrayListOf("Energy +15\nEishin Flash bond +5", "Speed +5\nGuts +5\nEishin Flash bond +5"),
				
				// SR Speed 0500・定刻通り
				"良き管理者たるもの" to arrayListOf("Maximum Energy +4\nEnergy +10\nMotivation +1"),
				"良き友人たるもの" to arrayListOf("Energy +20\n(random)末脚 hint +1"),
			),
			"El Condor Pasa" to mapOf(
				// R Power トレセン学園
				"シークレット・ノート" to arrayListOf("Power +10\nEl Condor Pasa bond +5", "晴れの日○ hint +1\nEl Condor Pasa bond +5"),
				"メラメラ・ファイアー！" to arrayListOf("Stamina +10\nEl Condor Pasa bond +5", "Energy -10\nPower +20\nEl Condor Pasa bond +5"),
				
				// SSR Power パッションチャンピオーナ！
				"世界級の……！？" to arrayListOf("Power +5\nGuts +5"),
				"ダシが重要！！" to arrayListOf("Energy +30\nEl Condor Pasa bond +5", "スタミナキープ hint +1\nEl Condor Pasa bond +5"),
				"ルチャドーラ・エル！！" to arrayListOf("Power +10\nキラーチューン hint +1"),
			),
			"Fine Motion" to mapOf(
				// R Intelligence トレセン学園
				"ときめきシューズ" to arrayListOf("Speed +5\nSkill points +10\nFine Motion bond +5", "Energy -10\nStamina +5\nSkill points +20\nFine Motion bond +5"),
				"思い出クローバー" to arrayListOf("コーナー巧者○ hint +1\nFine Motion bond +5", "Guts +15\nFine Motion bond +5"),
				
				// SSR Intelligence 感謝は指先まで込めて
				"素敵な♪練習日和" to arrayListOf("Intelligence +5\nSkill points +20\nFine Motion bond +5", "Speed +10\nStamina +5", "Get Good Practice ○ status\nFine Motion bond +5"),
				"素敵な♪ライブ日和" to arrayListOf("Energy +10", "Skill points +10"),
				"素敵な♪レース日和" to arrayListOf("Energy -10\nGuts +5\nIntelligence +5/+10\nSkill points +15\nスピードスター hint +1/+3 or 抜け出し準備 hint +1"),
				"aaa" to arrayListOf("aaa"),
			),
			"Fuji Kiseki" to mapOf(
				// R Intelligence トレセン学園
				"スライハンド" to arrayListOf("Intelligence +5\nSkill points +15\nFuji Kiseki bond +5", "Power +5\nSkill points +15\nFuji Kiseki bond +5"),
				"ミスディレクション" to arrayListOf("抜け出し準備 hint +1\nFuji Kiseki bond +5", "Skill points +30\nFuji Kiseki bond +5"),
				
				// SR Intelligence やれやれ、お帰り
				"アブラカダブラ" to arrayListOf("Intelligence +10"),
				"アウトオブディスワールド" to arrayListOf("Intelligence +10\nSkill points +15\n追込焦り hint +1"),
			),
			"Gold City" to mapOf(
				// R Speed トレセン学園
				"08:36/朝寝坊、やばっ" to arrayListOf("Motivation -1\nSkill points +45\nGold City bond +5", "Energy +10\nIntelligence +5\nGold City bond +5"),
				"13:12/昼休み、気合い入れなきゃ" to arrayListOf("Skill points +30\nGold City bond +5", "小休憩 hint +1\nGold City bond +5"),
				
				// SSR Speed Run(my)way
				"Pride" to arrayListOf("Energy +20"),
				"Struggle" to arrayListOf("Energy +10\nStamina +10"),
				"One Step" to arrayListOf("Stamina +10\n慧眼 hint +1"),
			),
			"Gold Ship" to mapOf(
				// R Stamina トレセン学園
				"冒険家ゴールドシップ" to arrayListOf("Stamina +15\nGold Ship bond +5", "Guts +10\nSkill points +15\nGold Ship bond +5"),
				"甦れ！ゴルシ印のソース焼きそば！" to arrayListOf("Motivation +1\nStamina +5", "阪神レース場○ hint +1\nGold Ship bond +5"),
				
				// SSR Stamina 不沈艦の進撃
				"熱血ッ！エアバスケッ！" to arrayListOf("Energy -10\nStamina +25"),
				"リスペクトシャケッ！" to arrayListOf("Energy -10\nGuts +25"),
				"強敵と書いて『とも』と読むッ！" to arrayListOf("Stamina +10\n曲線のソムリエ hint +1"),
			),
			"Grass Wonder" to mapOf(
				// R Guts トレセン学園
				"文殿、思ひ煩ふ" to arrayListOf("Intelligence +10\nGrass Wonder bond +5", "Guts +5\nIntelligence +5\nGrass Wonder bond +5"),
				"昼つ方、打ち語らふ" to arrayListOf("先行駆け引き hint +1\nGrass Wonder bond +5", "徹底マーク○ hint +1\nGrass Wonder bond +5"),
				
				// SSR Guts 千紫万紅にまぎれぬ一凛
				"密やかなる熱情" to arrayListOf("Energy +10\nIntelligence +5\nSkill points +15\nGrass Wonder bond +5"),
				"ひととき、うらうらに" to arrayListOf(
					"Energy +15\nGrass Wonder bond +5", "One of these will be selected at random\n----------\nEnergy -10\nPower +5\nGuts +5\nIntelligence +5\nGrass Wonder bond +5" +
							"\n----------\nPower +5\nGuts +5\nIntelligence +10\nGrass Wonder bond +5"),
				"秘すれば、花なり" to arrayListOf(
					"Energy -10\nSpeed +5\nGuts +5\nIntelligence +5\n上昇気流 hint +1\nGrass Wonder bond +5",
					"Energy -10\nSpeed +5\nGuts +10\nIntelligence +5\n豪脚 hint +3\nGrass Wonder bond +5"),
			),
			"Haru Urara" to mapOf(
				// R Guts トレセン学園
				"うららん☆テスト勉強" to arrayListOf("Energy +10\nHaru Urara bond +5\nIntelligence +5", "Motivation +1\nIntelligence +5"),
				"うららん☆ふくへーダッシュ！" to arrayListOf("伏兵○ hint +1\nHaru Urara bond +5", "Motivation +1\nEnergy +10\nHaru Urara bond +5"),
				
				// SSR Guts うらら～な休日
				"ライスちゃんと頑張る！" to arrayListOf("Energy +10"),
				"キングちゃんと頑張る！" to arrayListOf("Motivation +1\nHaru Urara bond +5\nEnergy +10"),
				"みんなの応援で頑張る" to arrayListOf("Energy +15\nHaru Urara bond +5\nどこ吹く風 hint +1"),
			),
			"Hishi Akebono" to mapOf(
				// R Guts トレセン学園
				"どーんと☆召し上がれ♪" to arrayListOf("Energy +10\nHishi Akebono bond +5", "Energy -5\nPower +15\nHishi Akebono bond +5"),
				"どーんと☆お任せあれ♪" to arrayListOf("Stamina +10\nHishi Akebono bond +5", "Energy -15\nスプリントギア hint +2\nHishi Akebono bond +5"),
				
				// SSR Guts 召しませふぁーすとBite！
				"そのままの自分でボーノボーノ♪" to arrayListOf("Maximum Energy +4\nGuts +5\nHishi Akebono bond +5"),
				"はっけよーい……ボーノ☆" to arrayListOf(
					"Energy +30\nGuts +5\nHishi Akebono bond +5\n(random) Get Overweight status",
					"Stamina +5\nPower +5\nHishi Akebono bond +5"),
				"あなたにボーノ、あたしもボーノ☆" to arrayListOf("Stamina +5\nGuts +10\nシックスセンス hint +3 or 危険回避 hint +3"),
			),
			"Hishi Amazon" to mapOf(
				// R Power トレセン学園
				"ヒシアマ姐さん奮闘記～問題児編～" to arrayListOf(
					"Energy +10\nIntelligence +5\nHishi Amazon bond +5",
					"Energy -10\nSpeed +10\nGuts +5\nHishi Amazon bond +5"),
				"ヒシアマ姐さん奮闘記～追い込み編～" to arrayListOf(
					"追込ためらい hint +1\nHishi Amazon bond +5",
					"Power +5\nSkill points +15\nHishi Amazon bond +5"),
				
				// SR Power テッペンに立て！
				"アタシはアタシらしく" to arrayListOf("Speed +5\nPower +5\nHishi Amazon bond +5"),
				"姉御流の解決法" to arrayListOf("Speed +10\n仕掛け抜群 hint +1"),
			),
			"Ikuno Dictus" to mapOf(
				// R Intelligence トレセン学園
				"イクノ式万全メソッド" to arrayListOf("Intelligence +10\nIkuno Dictus bond +5", "Skill points +30\nIkuno Dictus bond +5"),
				"イクノ式マネジメント論" to arrayListOf("Stamina +20\nIkuno Dictus bond +5", "トリック（後）hint +1\nIkuno Dictus bond +5"),
				
				// SR Intelligence 準備運動は怠るべからず
				"イクノ式友情メソッド" to arrayListOf("Intelligence +10\nSkill points +20"),
				"イクノ式サポートメソッド" to arrayListOf(
					"Intelligence +15\n逃げ駆け引き hint +3\nIkuno Dictus bond +5",
					"Intelligence +15\n追込駆け引き hint +3\nIkuno Dictus bond +5"),
			),
			"Ines Fujin" to mapOf(
				// R Guts トレセン学園
				"鬼ごっこ、なの！" to arrayListOf(
					"Energy +10\nSpeed +5\nInes Fujin bond +5",
					"急ぎ足 hint +1\nInes Fujin bond +5"),
				"あと10分、なの！" to arrayListOf(
					"Guts +15\nInes Fujin bond +5",
					"Intelligence +15\nInes Fujin bond +5"),
				
				// SSR Guts 飛び出せ、キラメケ
				"お姉ちゃんの瞬発力" to arrayListOf("Energy -10\nSpeed +5\nStamina +5\nGuts +10"),
				"ハピハピなる前進" to arrayListOf("Energy -10\nGuts +10\nSkill points +30\nInes Fujin bond +5"),
				"目指せ、きらめく瞬間！" to arrayListOf("One of these will be selected at random\nEnergy -10\nGuts +10\nじゃじゃウマ娘 hint +1 or\n勢い任せ hint +1\nEnergy -10\nGuts +20\nIntelligence +5\nじゃじゃウマ娘 hint +3"),
			),
			"Kawakami Princess" to mapOf(
				// R Speed トレセン学園
				"プリンセス、殴る！" to arrayListOf(
					"Guts +10\nKawakami Princess bond +5",
					"Motivation +1\nKawakami Princess bond +5"),
				"プリンセス、逃走！" to arrayListOf(
					"Energy +10\nKawakami Princess bond +5",
					"食い下がり hint +1\nKawakami Princess bond +5"),
				
				// SSR Speed 花嫁たるもの！！
				"つつましく！豪快に！" to arrayListOf(
					"Motivation +1\nSpeed +5\nKawakami Princess bond +5",
					"Skill points +10\n束縛 hint +1\nKawakami Princess bond +5"),
				"プリンセスパワー☆フルマックス！" to arrayListOf("Speed +5\nGuts +5"),
				"ぶちかませ！お姫様ロード！" to arrayListOf(
					"Speed +15\nGuts +15\n注目の踊り子 hint +1\nKawakami Princess bond +5",
					"Energy +25\nSkill points +25"),
			),
			"King Halo" to mapOf(
				// R Speed トレセン学園
				"付き合う権利をあげる！" to arrayListOf(
					"Energy -20\nSpeed +10\nPower +10\nIntelligence +5\nKing Halo bond +5",
					"Motivation -1\nGuts +25\nKing Halo bond +5"),
				"助言する権利をあげる！" to arrayListOf(
					"Guts +10\nIntelligence +5\nKing Halo bond +5",
					"末脚 hint +1\nKing Halo bond +5"),
				
				// SR Speed 一流プランニング
				"一流の指導" to arrayListOf("Speed +10"),
				"一流の相談相手" to arrayListOf("Speed +15\nSkill points +15"),
			),
			"Kitasan Black" to mapOf(
				// R Speed トレセン学園
				"あぁ、故郷" to arrayListOf("Speed +5\nPower +10\nKitasan Black bond +5", "Get Good Practice ○ status\nKitasan Black bond +5"),
				"あぁ、友情" to arrayListOf("Motivation +1\nPower +5\nKitasan Black bond +5", "Energy +10\nKitasan Black bond +5"),
				
				// SSR Speed 迫る熱に押されて
				"輝き追いかけて" to arrayListOf("Motivation +1\nSpeed +2\nPower +2\nIntelligence +2\nKitasan Black bond +5"),
				"情けは人のためならず" to arrayListOf("Energy +10\nMotivation +1\nKitasan Black bond +5", "Speed +5/+10\n直線巧者 hint +1/+3\nKitasan Black bond +5"),
				"2人で行くこの花道" to arrayListOf(
					"One of these will be selected at random\n----------\nSpeed +5\nPower +5\nKitasan Black bond +5\n弧線のプロフェッサー hint +1\n----------\nSpeed +10\nPower +10\n" +
							"Kitasan Black bond +5\n弧線のプロフェッサー hint +3"),
			),
			"Manhattan Cafe" to mapOf(
				// R Stamina トレセン学園
				"夜の独走" to arrayListOf(
					"Stamina +10\nManhattan Cafe bond +5",
					"Energy +10\nStamina +5\nManhattan Cafe bond +5"),
				"静けさを嗜む" to arrayListOf(
					"Stamina +5\nSkill points +15\nManhattan Cafe bond +5",
					"非根幹距離○ hint +1\nManhattan Cafe bond +5"),
				
				// SR Stamina 雨の独奏、私の独創
				"雨のお誘い" to arrayListOf("Energy +15\nSkill points +15"),
				"大雨のお誘い" to arrayListOf("Stamina +15\n読解力 hint +1\nManhattan Cafe bond +5"),
			),
			"Maruzensky" to mapOf(
				// R Speed トレセン学園
				"可愛い後輩ちゃんのためだもの" to arrayListOf(
					"先駆け hint +1",
					"Energy +5\nSpeed +10"),
				"マブいドライブだっちゅ～の" to arrayListOf(
					"Motivation +1\nSpeed +5",
					"Motivation +1\nIntelligence +5"),
			),
			"Marvelous Sunday" to mapOf(
				// R Intelligence トレセン学園
				"マーベラスにするために☆" to arrayListOf(
					"Energy +10\nMotivation +1\nMarvelous Sunday bond +5",
					"阪神レース場○ hint +1\nMarvelous Sunday bond +5"),
				"問答無用のマーベラス☆" to arrayListOf(
					"Energy +10\nSpeed +5\n(random) Marvelous Sunday bond +5",
					"Motivation +1\nSpeed +5\nMarvelous Sunday bond +5"),
				
				// SR Intelligence マーベラス☆大作戦
				"世界マーベラス計画☆" to arrayListOf("Guts +5\nIntelligence +5"),
				"とってもすごいよマーベラス☆" to arrayListOf("Guts +15\nIntelligence +15"),
			),
			"Matikane Tannhauser" to mapOf(
				// R Guts トレセン学園
				"これも普通の努力です！" to arrayListOf(
					"Speed +10\nMatikane Tannhauser bond +5",
					"Power +10\nMatikane Tannhauser bond +5"),
				"これも普通のハプニング！？" to arrayListOf(
					"Stamina +5\nGuts +10\nMatikane Tannhauser bond +5",
					"逃げけん制 hint +1\nMatikane Tannhauser bond +5"),
				
				// SSR Guts Just keep going.
				"求む、個性！" to arrayListOf(
					"Motivation +1\nMatikane Tannhauser bond +5",
					"Energy +10/+30\nMatikane Tannhauser bond +5"),
				"目指せ、いたずらっ子！" to arrayListOf("Power +5\nSkill points +10\nMatikane Tannhauser bond +5"),
				"個性的な走り方" to arrayListOf(
					"One of these will be selected at random\n----------\nPower +10\nSkill points +15\nMatikane Tannhauser bond +5\nどこ吹く風 hint +3\n----------\nPower +5\nSkill points +10" +
							"\nMatikane Tannhauser bond +5\nウマ込み冷静 hint +3"),
			),
			"Matikanefukukitaru" to mapOf(
				// R Intelligence トレセン学園
				"全力スピリチュアル" to arrayListOf(
					"Intelligence +5\nSkill points +15\nMatikanefukukitaru bond +5",
					"Energy -10\nSpeed +5\nStamina +5\nPower +5\nMatikanefukukitaru bond +5"),
				"信仰心と親切心が交わる時ーー" to arrayListOf(
					"Skill points +30\nMatikanefukukitaru bond +5",
					"Energy +20\nMatikanefukukitaru bond +5"),
				
				// SR Intelligence 運の行方
				"お助けください、こっくり様！" to arrayListOf("Energy +5\nMotivation +1\nSkill points +15\nMatikanefukukitaru bond +5"),
				"お導きとお友だち" to arrayListOf(
					"Skill points +45\nMatikanefukukitaru bond +5",
					"(random) Energy -20\n(random) Energy +10\n右回り○ hint +1/+3\nMatikanefukukitaru bond +5\n(random) Motivation +1"),
			),
			"Mayano Top Gun" to mapOf(
				// R Stamina トレセン学園
				"マヤノ的おやつ会議！" to arrayListOf(
					"Stamina +5\nGuts +5\nMayano Top Gun bond +5",
					"Stamina +10\nMayano Top Gun bond +5"),
				"マヤノ的ファッション会議！" to arrayListOf(
					"直線巧者 hint +1\nMayano Top Gun bond +5",
					"Stamina +10\nMayano Top Gun bond +5"),
				
				// SR Stamina カワイイ＋カワイイは～？
				"まだまだタキシング中？" to arrayListOf("Skill points +30\nMayano Top Gun bond +5"),
				"トレンドへテイクオフ♪" to arrayListOf("Stamina +10\nSkill points +15\n集中力 hint +1\nMayano Top Gun bond +5"),
			),
			"Meishodoto" to mapOf(
				// R Guts トレセン学園
				"私……改革ですっ" to arrayListOf(
					"Energy +10\nMotivation +1\nMeishodoto bond +5",
					"Guts +15\nMeishodoto bond +5"),
				"にんじん……買ってくださいっ" to arrayListOf(
					"Energy +10\nIntelligence +5\nMeishodoto bond +5",
					"先行コーナー○ hint +1\nMeishodoto bond +5"),
				
				// SR Guts 幸せと背中合わせ
				"私の、運勢……" to arrayListOf(
					"Energy +10\nGuts +5\nMeishodoto bond +5",
					"Maximum Energy +4\nMotivation +1\n(random) Guts +5\n(random) Intelligence +5\nMeishodoto bond +5\n(random) Energy -10"),
				"私の、欲しいもの……" to arrayListOf("Energy +10\nMotivation +1\nSkill points +15\n抜け出し準備 hint +1"),
			),
			"Mejiro Dober" to mapOf(
				// R Intelligence トレセン学園
				"喜んでくれるかな……" to arrayListOf("Skill points +45\nMejiro Dober bond +5", "負けん気 hint +1\nMejiro Dober bond +5"),
				"やってみてもいい" to arrayListOf("Energy +15\nMejiro Dober bond +5", "Motivation +1\nSkill points +15\nMejiro Dober bond +5"),
				
				// SR Intelligence 目線は気にせず
				"褒められたって" to arrayListOf("Energy +10\nGuts +5"),
				"見られてたって" to arrayListOf("Energy +5/+20\nStamina +5/+15\n差し切り体勢 hint +1"),
				
				// SSR Intelligence おもい、ねがう
				"踏み出す、一歩" to arrayListOf("Energy -10\nSkill points +15~45\nMejiro Dober bond +5", "Guts +5\nIntelligence +5\nMejiro Dober bond +5"),
				"高め合い、二歩" to arrayListOf("Guts +3\nIntelligence +3\nSkill points +5"),
				"三歩進み、二歩下がって、一歩" to arrayListOf("Energy -10\nGuts +5\nIntelligence +5\n大局観 hint +1"),
			),
			"Mejiro McQueen" to mapOf(
				// R Stamina トレセン学園
				"体重を保つ為" to arrayListOf(
					"Energy -10\nStamina +15\nMejiro McQueen bond +5",
					"Maximum Energy +4\nStamina +5\nMejiro McQueen bond +5"),
				"高みに至る為" to arrayListOf(
					"Stamina +5\nGuts +5\nMejiro McQueen bond +5",
					"先駆け hint +1\nMejiro McQueen bond +5"),
				
				// SSR Stamina 『エース』として
				"“期待”は力" to arrayListOf("Stamina +10"),
				"“期待”は好意" to arrayListOf("Motivation +1\nStamina +10"),
				"“期待”に応える" to arrayListOf("One of these will be selected at random\n----------\nEnergy -10\nStamina +10\nクールダウンhint +3\n----------\nEnergy -10\n深呼吸hint +1"),
			),
			"Mejiro Palmer" to mapOf(
				// R Guts トレセン学園
				"逃げられない選択？" to arrayListOf(
					"Energy -15\nGuts +20\nMejiro Palmer bond +5",
					"Power +5\nSkill points +15\nMejiro Palmer bond +5"),
				"ポジティブな逃げ" to arrayListOf(
					"Guts +15\nMejiro Palmer bond +5",
					"道悪○ hint +1\nMejiro Palmer bond +5"),
				
				// SSR Guts バカと笑え
				"振り逃げランナウェイ" to arrayListOf(
					"Energy -15\nStamina +10\nGuts +10\nMejiro Palmer bond +5",
					"Energy -15\nGuts +10\nIntelligence +10\nMejiro Palmer bond +5"),
				"ラブ逃げエスケープ" to arrayListOf(
					"Energy +10\nGuts +5\nIntelligence +5\nMejiro Palmer bond +5",
					"Energy +10\n逃げのコツ○ hint +1\nMejiro Palmer bond +5"),
				"ポジティブ逃げネバギバ！" to arrayListOf(
					"Energy -20\nStamina +5\nGuts +5\n先陣の心得 hint +3 or\nリードキープ hint +1/+3\nMejiro Palmer bond +5",
					"Energy +10\n一匹狼 hint +1"),
			),
			"Mejiro Ryan" to mapOf(
				// R Power トレセン学園
				"あくまで薦められただけ" to arrayListOf(
					"ペースキープ hint +1\nMejiro Ryan bond +5",
					"Energy +30\nMejiro Ryan bond +5"),
				"筋肉と共に明日へ！" to arrayListOf(
					"Energy -10\nPower +15\nMejiro Ryan bond +5",
					"Maximum Energy +4\nPower +5\nMejiro Ryan bond +5"),
				
				// SR Power 鍛えて、応えて！
				"かっこいい理由" to arrayListOf("Power +5\nSkill points +15\nMejiro Ryan bond +5"),
				"応援を受け入れる気持ち" to arrayListOf("Power +15\n垂れウマ回避 hint +1\nMejiro Ryan bond +5"),
			),
			"Mihono Bourbon" to mapOf(
				// R Power トレセン学園
				"他人に危害を及ぼしてはならない" to arrayListOf(
					"Energy -10\nStamina +5\nPower +15\nMihono Bourbon bond +5",
					"Energy +10\nIntelligence +5\nMihono Bourbon bond +5"),
				"命令は守らなければならない" to arrayListOf(
					"集中力 hint +1\nMihono Bourbon bond +5",
					"Speed +10\nSkill points +15\nMihono Bourbon bond +5"),
				
				// SR Power 鍛えぬくトモ
				"サイボーグではありません" to arrayListOf(
					"Guts +10\nSkill points +15\nMihono Bourbon bond +5",
					"Energy -10\nコーナー回復○ hint +1\n(random) Mihono Bourbon bond -5\nEvent chain ended"),
				"新たな価値観のインストール" to arrayListOf("Energy −10\nPower +5\nGuts +5\n逃げ直線○ hint +1\nMihono Bourbon bond +5"),
			),
			"Narita Taishin" to mapOf(
				// R Speed トレセン学園
				"別に、ほっといて" to arrayListOf(
					"Stamina +5\nSkill points +15\nNarita Taishin bond +5",
					"Power +5\nSkill points +15\nNarita Taishin bond +5"),
				"別に、邪魔しないで" to arrayListOf(
					"追い上げ hint +1\nNarita Taishin bond +5",
					"Skill points +30\nNarita Taishin bond +5"),
				
				// SR Speed 波立つキモチ
				"夜にこっそり……？" to arrayListOf("Speed +5\nStamina +5"),
				"エンジョイミュージカル！" to arrayListOf("Speed +5\nStamina +5\nPower +5\nお見通し hint +1"),
			),
			"Nice Nature" to mapOf(
				// R Guts トレセン学園
				"（ニャンとも）ガラじゃない" to arrayListOf(
					"Energy +20\nNice Nature bond +5",
					"Energy +10\nIntelligence +5\nNice Nature bond +5"),
				"（美味しい）おせっかい" to arrayListOf(
					"ペースアップ hint +1\nNice Nature bond +5",
					"Motivation +1\nMaximum Energy +4"),
				
				// SR Guts …ただの水滴ですって
				"（頑張り上手の）ほどほど上手" to arrayListOf("Stamina +5\nGuts +5"),
				"見えてる結果（でも！）" to arrayListOf("Stamina +5\nGuts +10\n別腹タンク hint +1"),
				
				// SR Intelligence むじゃむじゃむじゃき
				"背、追いかけて" to arrayListOf(
					"Energy +5\nIntelligence +3\nNice Nature bond +5",
					"Nice Nature bond +20"),
				"背、追い風を受けて" to arrayListOf("Energy +10\nIntelligence +5\n伏兵○ hint +3\nNice Nature bond +5"),
			),
			"Nishino Flower" to mapOf(
				// R Speed トレセン学園
				"きれいに咲こうねっ♪" to arrayListOf(
					"Intelligence +15\nNishino Flower bond +5",
					"Speed +10\nPower +5\nNishino Flower bond +5"),
				"温もり愛情弁当" to arrayListOf(
					"Get Charming ○ status\nNishino Flower bond +5",
					"Energy +20\nNishino Flower bond +5"),
				
				// SR Power あなたにささげる
				"休んでほしくて……！" to arrayListOf("Motivation +1\nSpeed +3\nPower +3\nIntelligence +3"),
				"ありがとうを言いたくて……！" to arrayListOf(
					"Power +5\n直線巧者 hint +1\nNishino Flower bond +5",
					"Intelligence +5\n直線加速 hint +1\nNishino Flower bond +5"),
				
				// SSR Speed まだ小さな蕾でも
				"私にできること" to arrayListOf("Maximum Energy +4\nSkill points +10"),
				"あこがれの……" to arrayListOf(
					"Energy -10\nIntelligence +20\nNishino Flower bond +5",
					"Intelligence +5\nSkill points +15"),
				"たいせつなのは！" to arrayListOf("Speed +5\nIntelligence +10\nハヤテ一文字 hint +1/+3 or 直線巧者 hint +1"),
			),
			"Oguri Cap" to mapOf(
				// R Power トレセン学園
				"何と答えれば……" to arrayListOf(
					"Energy +5\nPower +5",
					"Energy -10\nGuts +15"),
				"人混みは大変だ……" to arrayListOf(
					"Power +5\nSkill points +15",
					"中山レース場○ hint +1"),
				
				// SSR Power 『愛してもらうんだぞ』
				"ぬいぐるみか……" to arrayListOf("Maximum Energy +4\nPower +5"),
				"本当に応えるべきは……" to arrayListOf(
					"Power +5\nSkill points +10\nスタミナキープ hint +1\nOguri Cap bond +5",
					"Stamina +5\nSkill points +10\n外差し準備 hint +1\nOguri Cap bond +5"),
				"私が語りたいことは……" to arrayListOf(
					"One of these will be selected at random\n----------\nPower +10\nGuts +5\nSkill points +10\nOguri Cap bond +5\n豪脚 hint +1\n----------\nPower +15\nGuts +10\nSkill points +15\n" +
							"Oguri Cap bond +5\n豪脚 hint +1",
					"Energy +30"),
			),
			"Rice Shower" to mapOf(
				// R Stamina トレセン学園
				"お花屋さんでの1ページ" to arrayListOf(
					"Motivation +1\nRice Shower bond +5",
					"Stamina +10\nRice Shower bond +5"),
				"曇りの日の1ページ" to arrayListOf(
					"Speed +5\nGuts +5\nRice Shower bond +5",
					"良バ場○ hint +1\nRice Shower bond +5"),
				
				// SSR Stamina 『幸せ』が舞う時
				"風の強い日の1ページ" to arrayListOf("Guts +10\nRice Shower bond +5"),
				"友達と頑張った日の1ページ" to arrayListOf("Stamina +10\nRice Shower bond +5"),
				"2人で頑張った日の1ページ" to arrayListOf("Energy -10\n火事場のバ鹿力 hint +1 or 別腹タンク hint +1\nRice Shower bond +5"),
			),
			"Sakura Bakushin O" to mapOf(
				// R Speed トレセン学園
				"優等生の一石二鳥！！" to arrayListOf(
					"Speed +15\nSakura Bakushin O bond +5",
					"Speed +5\nPower +10\nSakura Bakushin O bond +5"),
				"走りだすほどの！！" to arrayListOf(
					"詰め寄り hint +1",
					"Energy -10\nSpeed +10\nPower +5\nSakura Bakushin O bond +5"),
				
				// SSR Speed はやい！うまい！はやい！
				"バクシンの料理！！" to arrayListOf("Speed +10"),
				"バクシンの気持ち！！" to arrayListOf("Speed +5\nPower +5"),
				"バクシンの大成功！！" to arrayListOf("Speed +10\nPower +5\nスプリントターボ hint +1"),
			),
			"Sakura Chiyono O" to mapOf(
				// R Stamina トレセン学園
				"今日の格言！" to arrayListOf(
					"Energy -10\nPower +20\nSakura Chiyono O bond +5",
					"Energy +5\nSkill points +10\nSakura Chiyono O bond +5"),
				"いつか咲く、その日まで……" to arrayListOf(
					"Energy +5\nStamina +5\nSakura Chiyono O bond +5",
					"春ウマ娘○ hint +1\nSakura Chiyono O bond +5"),
				
				// SSR Stamina 今ぞ盛りのさくら花
				"花咲く、一歩！" to arrayListOf("Skill points +15"),
				"花咲く、希望！" to arrayListOf(
					"One of these will be selected at random\n----------\nStamina +15\nHeal a negative status effect\nSakura Chiyono O bond +5\n----------\nEnergy -10\nGuts +15" +
							"\nSakura Chiyono O bond +5",
					"Energy +10\nMotivation +1\nSakura Chiyono O bond +5"),
				"花咲く、いつか！" to arrayListOf(
					"One of these will be selected at random\n----------\nEnergy -15\nStamina +20\nSkill points +10\nスピードスター hint +3\nSakura Chiyono O bond +5\n----------\nEnergy -15\nStamina +10\n" +
							"Skill points +5\n抜け出し準備 hint +3\nSakura Chiyono O bond +5",
					"Speed +5\nStamina +10\nPower +5\nSkill points +30\nSakura Chiyono O bond +5"),
			),
			"Satono Diamond" to mapOf(
				// R Stamina トレセン学園
				"新しいもの、大好きです！" to arrayListOf(
					"Guts +10\nSatono Diamond bond +5",
					"Energy -10\nStamina +20\nSatono Diamond bond +5"),
				"難しいこと、大好きです！" to arrayListOf(
					"Stamina +5\nGuts +10\nSatono Diamond bond +5",
					"逃げためらい hint +1\nSatono Diamond bond +5"),
				
				// SSR Stamina その背中を越えて
				"『望まれた』ウマ娘" to arrayListOf("Energy +10\nStamina +2\nGuts +2\nIntelligence +2"),
				"ダイヤのこだわり" to arrayListOf(
					"Intelligence +10\nSatono Diamond bond +5",
					"One of these will be selected at random\n----------\nEnergy +15\nStamina +10\nSatono Diamond bond +5\n----------\nMotivation -1\nGuts +20"),
				"あなたにだけは絶対に" to arrayListOf(
					"Energy -20\nStamina +30\n鋼の意志 hint +1\nSatono Diamond bond +5",
					"Energy +5\nGuts +5\n鋼の意志 hint +1\nSatono Diamond bond +5"),
			),
			"Seeking the Pearl" to mapOf(
				// R Guts トレセン学園
				"全力でシンキング！" to arrayListOf(
					"Intelligence +20\nSeeking the Pearl bond +5",
					"Energy -10\nウマ好み hint +3\nSeeking the Pearl bond +5"),
				"全力でパッション！" to arrayListOf(
					"Energy +10\nMotivation +1",
					"Power +5\nGuts +5\nSeeking the Pearl bond +5"),
				
				// SR Guts 世界の真珠、その名は
				"言葉はノンノン♪ ボディで語るの！" to arrayListOf(
					"Motivation +1\nラッキーセブン hint +1\nSeeking the Pearl bond +5",
					"Power +10\nGuts +10\nSeeking the Pearl bond +5",
					"Energy +30"),
				"諦めないで！ 可能性は無限大！" to arrayListOf("Power +5\nGuts +5\nギアシフト hint +1"),
			),
			"Seiun Sky" to mapOf(
				// R Stamina トレセン学園
				"ゆる募、助言者" to arrayListOf(
					"Intelligence +15\nSeiun Sky bond +5",
					"リードキープ hint +1\nSeiun Sky bond +5"),
				"ゆる募、ネコの捕まえ方" to arrayListOf(
					"Energy +10\nIntelligence +5\nSeiun Sky bond +5",
					"Energy -10\nSpeed +15\nStamina +5\nSeiun Sky bond +5"),
				
				// SSR Stamina 待望の大謀
				"ご利用は戦略的に☆" to arrayListOf(
					"Energy +10\nIntelligence +5\nSeiun Sky bond +5",
					"Skill points +30\n二の矢 hint +1\nSeiun Sky bond -5\nEvent chain ended"),
				"トラップにご注意" to arrayListOf("Speed +5\nStamina +5"),
				"すべては勝つために" to arrayListOf("Energy +10\nMotivation +1\nSpeed +5\nStamina +5\nIntelligence +5\nSkill points +5\n脱出術 hint +1/+3 or 急ぎ足 hint +1"),
			),
			"Silence Suzuka" to mapOf(
				// R Speed トレセン学園
				"どこまでも" to arrayListOf("Speed +10\nStamina +5\nSilence Suzuka bond +5", "Speed +15\nSilence Suzuka bond +5"),
				"どうすれば" to arrayListOf(
					"Speed +5\nStamina +5\nIntelligence +5\nSilence Suzuka bond +5", "One of these will be selected at random\n----------\nSpeed +15\nSilence Suzuka bond +5\n----------\n" +
							"左回り○ hint +1\nSilence Suzuka bond +5"),
				
				// SSR Speed 輝く景色の、その先に
				"手紙→？" to arrayListOf("Speed +10"),
				"手紙→皆の気持ち→？" to arrayListOf("Speed +10\nPower +10"),
				"手紙→皆の気持ち→ちゃんと待っててね" to arrayListOf("Speed +10\n逃亡者 hint +1"),
			),
			"Smart Falcon" to mapOf(
				// R Power トレセン学園
				"ライブはコーレスが命☆" to arrayListOf(
					"Stamina +5\nGuts +10\nSmart Falcon bond +5",
					"Intelligence +15\nSmart Falcon bond +5"),
				"かわいかったら見に来てね☆" to arrayListOf(
					"Energy -10\nPower +10\nhint +1\nSmart Falcon bond +5",
					"Energy +10\nIntelligence +5\nSmart Falcon bond +5"),
				
				// SSR Power これが私のウマドル道☆
				"常に心にステージを☆" to arrayListOf(
					"Intelligence +10\nSmart Falcon bond +5",
					"Energy +25\n集中力 hint +1\nSmart Falcon bond +5\nEvent chain ended"),
				"いつでもどこでも輝くよ☆" to arrayListOf("Speed +5\nPower +5"),
				"これが私のウマドル道☆" to arrayListOf(
					"Maximum Energy +4\nStamina +5\nPower +5\nIntelligence +5\nポジションセンス hint +1",
					"Maximum Energy +4\nStamina +5\nPower +5\nIntelligence +5\n注目の踊り子 hint +3"),
			),
			"Special Week" to mapOf(
				// R Guts トレセン学園
				"あれもこれもで、悩んじゃいます！" to arrayListOf(
					"Energy +10\nMotivation +1\nSpecial Week bond +5",
					"Energy -10\nStamina +15\nSkill points +15\nSpecial Week bond +5"),
				"曲がり角には、気をつけます！" to arrayListOf(
					"別腹タンク hint +1\nSpecial Week bond +5",
					"Guts +15\nSpecial Week bond +5"),
				
				// SSR Guts 日本一のステージを
				"蹄鉄で、ステップを！" to arrayListOf("Speed +5\nStamina +5"),
				"想いは、ジェットコースター！" to arrayListOf(
					"Energy -10\nSpeed +5\nStamina +5\nGuts +10\nSpecial Week bond +5",
					"Energy +20\nIntelligence +10\nSpecial Week bond +5\nEvent chain ended"),
				"私たちの、ビクトリースタンド！" to arrayListOf(
					"Energy -10\nSpeed +5\nStamina +5\nGuts +5\n全身全霊 hint +1 or 末脚 hint +1\nSpecial Week bond +5"),
				
				// SSR Speed 夕焼けはあこがれの色
				"いつかは憧れのウマ娘に！" to arrayListOf("Energy +10\nSpeed +5"),
				"少しでも近づけるように" to arrayListOf(
					"Energy -10\nSpeed +15\nSpecial Week bond +5",
					"Energy -10\nSkill points +20\nSpecial Week bond +5",
					"Energy -10\nふり絞り hint +1\nSpecial Week bond +5"),
				"憧れのカタチ" to arrayListOf(
					"One of these will be selected at random\n----------\nEnergy +10\nSpeed +5\n栄養補給 hint +1/+3\n----------\nEnergy +10\nSpeed +10\nSkill points +10\n食いしん坊 hint +3"),
			),
			"Super Creek" to mapOf(
				// R Stamina トレセン学園
				"お手伝いもお任せ♪" to arrayListOf(
					"Energy +15\nSuper Creek bond +5",
					"Stamina +10\nSuper Creek bond +5"),
				"お気遣いもお任せ♪" to arrayListOf(
					"深呼吸 hint +1\nSuper Creek bond +5",
					"Energy +10\nStamina +5\nSuper Creek bond +5"),
				
				// SSR Stamina 一粒の安らぎ
				"できたてはごろり、たっぷりと" to arrayListOf("Energy +10\nStamina +5"),
				"煮込んでとろり、優しい味に" to arrayListOf("Stamina +5\nPower +5"),
				"おかわりどうぞ、いいこたち！" to arrayListOf("Stamina +10\nPower +5\n円弧のマエストロ hint +1"),
			),
			"Sweep Tosho" to mapOf(
				// R Speed トレセン学園
				"ミラクル☆エスケープ" to arrayListOf(
					"Energy +10\nSpeed +5\nSweep Tosho bond +5",
					"Energy -10\nSpeed +20\nSweep Tosho bond +5"),
				"ワンダフル☆ミステイク！" to arrayListOf(
					"One of these will be selected at random\n----------\nEnergy -15\nSkill points +40\n----------\nEnergy -20\nSkill points +40\nSweep Tosho bond +5",
					"Get Charming ○ status\nSweep Tosho bond +5"),
				
				// SR Speed 見習い魔女と長い夜
				"とっておきのお友だち？" to arrayListOf(
					"Speed +5\nSkill points +10\nラッキーセブン hint +1\nSweep Tosho bond +5",
					"Motivation -1\nおひとり様○ hint +5"),
				"イタズラは計画的に" to arrayListOf(
					"Speed +10\nSkill points +20\n冷静 hint +1\nSweep Tosho bond +5",
					"Motivation -1\n一匹狼 hint +1"),
			),
			"Symboli Rudolf" to mapOf(
				// R Intelligence トレセン学園
				"\"皇帝\"の激励" to arrayListOf(
					"Speed +10",
					"Energy -10\nSkill points +30"),
				"生徒会長の思い" to arrayListOf(
					"雨の日○ hint +1",
					"Stamina +15"),
			),
			"TM Opera O" to mapOf(
				// R Stamina トレセン学園
				"有限の時を越えて" to arrayListOf("Energy +10\nSkill points +15", "非根幹距離○ hint +1"),
				"勝者へ至るエチュード" to arrayListOf(
					"Motivation -1\nSpeed +5\nSkill points +30",
					"Power +5\nSkill points +15"),
			),
			"Taiki Shuttle" to mapOf(
				// R Speed トレセン学園
				"イエス！ レッツ・ハグ☆" to arrayListOf(
					"Speed +10",
					"Speed +5\nPower +5"),
				"オゥ！トゥナイト・パーティー☆" to arrayListOf(
					"Energy -10\nSpeed +5\nPower +10",
					"抜け出し準備 hint +1"),
			),
			"Tamamo Cross" to mapOf(
				// R Stamina トレセン学園
				"負けられん戦い！" to arrayListOf(
					"ウマ込み冷静 hint +1\nTamamo Cross bond +5",
					"Stamina +5\nIntelligence +5\nTamamo Cross bond +5"),
				"タマモ先輩の学園案内" to arrayListOf(
					"Intelligence +10\nTamamo Cross bond +5",
					"Stamina +5\nGuts +5\nTamamo Cross bond +5"),
				
				// SSR Stamina 天をも切り裂くイナズマ娘！
				"牛・豚・鶏・カレー！" to arrayListOf("Stamina +5\nPower +5"),
				"なけなしのレイトショー" to arrayListOf("Stamina +5\nPower +5\nGuts +5"),
				"貧しさは友だ！" to arrayListOf("Stamina +5\nPower +10\n迅速果断 hint +1"),
			),
			"Tazuna Hayakawa" to mapOf(
				// R Friendship トレセン学園
				"お疲れ様です♪" to arrayListOf("(random) Motivation +1\nSpeed +3\nTazuna Hayakawa bond +5"),
				"選んだ生き方" to arrayListOf(
					"Energy +10\nMotivation +1\nTazuna Hayakawa bond +5",
					"Motivation +1\nIntelligence +5\nTazuna Hayakawa bond +5"),
				"情熱のふたり" to arrayListOf(
					"Energy +10\nIntelligence +5\nMotivation +1\nTazuna Hayakawa bond +5\nCan start dating",
					"Motivation -1\nTazuna Hayakawa bond -5\n展開窺い hint +1\nEvent chain ended"),
				
				// SSR Friendship ようこそ、トレセン学園へ！
				"牛乳ときどきリンゴ" to arrayListOf("Energy +35\nSpeed +5\nMotivation +1\nTazuna Hayakawa bond +5"),
				"驚異の逃げ脚？" to arrayListOf("Energy +35\nTazuna Hayakawa bond +5\nHeal a negative status effect"),
				"キネマの思ひ出" to arrayListOf(
					"Energy +35\nStamina +5\nMotivation +1\nTazuna Hayakawa bond +5",
					"Stamina +10\nGuts +10\nMotivation +1\nTazuna Hayakawa bond +5"),
				"ため息と絆創膏" to arrayListOf("Energy +50\nIntelligence +5\nMotivation +1\nTazuna Hayakawa bond +5\nHeal a negative status effect"),
				"ひと休みサプライズ" to arrayListOf("Energy +50\nSkill points +30\nMotivation +2\nTazuna Hayakawa bond +5\n集中力 hint +1\nコンセントレーション hint +1"),
			),
			"Tokai Teio" to mapOf(
				// R Speed トレセン学園
				"ボクの武器" to arrayListOf("Motivation +1\nGuts +10\nTokai Teio bond +5", "先行直線○ hint +1\nTokai Teio bond +5"),
				"ボクのやり方" to arrayListOf("Motivation +1\nSkill points +15\nTokai Teio bond +5", "Guts +15\nTokai Teio bond +5"),
				
				// SSR Speed 夢は掲げるものなのだっ！
				"幕開けの予感" to arrayListOf("Energy +10\nSpeed +5"),
				"一時の幕間" to arrayListOf("Energy +10\nSpeed +5\nIntelligence +5"),
				" 伝説のひと幕" to arrayListOf("Energy +10\nSpeed +5\n一陣の風 hint +1\nTokai Teio bond +5"),
				
				// SSR Power ふたつのノーブルライト
				"ライバルと2ショット" to arrayListOf("Power +10\nTokai Teio bond +5"),
				"ゴシップ狂想曲" to arrayListOf("Energy +10\nMotivation +1\nTokai Teio bond +5", "Energy -10\nPower +15\nSkill points +20\nTokai Teio bond +5\nEvent chain ended"),
				"灯火は未来へ輝く" to arrayListOf("Stamina +7\nPower +7\n火事場のバ鹿力 hint +1\nTokai Teio bond +5"),
			),
			"Twin Turbo" to mapOf(
				// R Speed トレセン学園
				"思い込んだら一直線！" to arrayListOf("Motivation -1\nSpeed +20", "Energy -10\nPower +20"),
				"燃えてきた！！" to arrayListOf("Energy +15\nTwin Turbo bond +5", "先駆け hint +1\nTwin Turbo bond +5"),
				
				// SSR Speed ターボエンジン全開宣言！
				"怖くないもん！" to arrayListOf(
					"One of these will be selected at random\n----------\nSpeed +10\nTwin Turbo bond +5\n----------\nEnergy -10\nSpeed +10\nEvent chain ended", "Energy +20\nEvent chain ended"),
				"捕まらないもん！" to arrayListOf(
					"One of these will be selected at random\n----------\nSpeed +15\n(random) 先頭プライド hint +1/+3\n----------\nEnergy -10\nSpeed +10\n(random) 先頭プライド hint +1/+3\nEvent " +
							"chain ended", "Energy +25\nEvent chain ended"),
				"ターボは強いんだもん！" to arrayListOf(
					"One of these will be selected at random\n----------\nEnergy -10\nSpeed +25\n先手必勝 hint +1/+3\n先駆け hint +1/+3\n----------\nEnergy -10\nSpeed +5\n先手必勝 hint +1/+3\n" +
							"先手必勝 hint +1/+3", "Energy +15\n展開窺い hint +1"),
			),
			"Vodka" to mapOf(
				// R Power トレセン学園
				"憧れのセリフ" to arrayListOf(
					"Power +10\nVodka bond +5",
					"Power +5\nSkill points +15\nVodka bond +5"),
				"大通りの強敵" to arrayListOf(
					"垂れウマ回避 hint +1\nVodka bond +5",
					"Power +5\nSkill points +15\nVodka bond +5"),
				
				// SSR Power ロード・オブ・ウオッカ
				"従うのは、自分の心" to arrayListOf("Power +5\nSkill points +15"),
				"夕日に誓った煌めき" to arrayListOf("Power +5\nSkill points +15"),
				"記録に残せよ？" to arrayListOf("Power +5\nSkill points +30\n好転一息 hint +1"),
			),
			"Winning Ticket" to mapOf(
				// R Guts トレセン学園
				"全・力・競・走！！" to arrayListOf(
					"差しコーナー○ hint +1\nWinning Ticket bond +5",
					"Skill points +30\nWinning Ticket bond +5"),
				"全・力・筋・肉！！" to arrayListOf(
					"Stamina +5\nSkill points +15\nWinning Ticket bond +5",
					"Motivation +1\nSkill points +15\nWinning Ticket bond +5"),
				
				// SSR Guts B・N・Winner!!
				"ユーザーネーム『W&T』" to arrayListOf("Guts +10"),
				"後日、全員筋肉痛！" to arrayListOf("Stamina +5\nGuts +5"),
				"We are BNW！！" to arrayListOf("Guts +10\n乗り換え上手 hint +1"),
				
				// SSR Power 夢はホントに叶うんだ！
				"密着！情熱ダービー特集ッ" to arrayListOf("Stamina +5\nPower +5"),
				"特訓！ライバルだからッ" to arrayListOf("Energy -10\nStamina +5\nPower +5"),
				"挑戦！夢は叶えるものッ" to arrayListOf("Energy -30\nStamina +5\nPower +5\n全身全霊 hint +2"),
			),
			"Yaeno Muteki" to mapOf(
				// R Power トレセン学園
				"剛毅朴訥、仁に近し" to arrayListOf("Speed +10"),
				"嗚呼、守りたい……！" to arrayListOf("Stamina +10\nPower +10", "中距離コーナー○ hint +1"),
				
				// SSR Power 押して忍べど燃ゆるもの
				"電脳奥義炸裂！ヤエノ出稽古修行" to arrayListOf("Speed +5\nPower +5\nSkill points +10"),
				"理の食VS暴の食" to arrayListOf("Energy +10\nSkill points +10\nYaeno Muteki bond +5", "Energy +10\nIntelligence +10\nSkill points +10\n遊びはおしまいっ！hint +3\nEvent chain ended"),
				"ヤエノムテキ恋歌地獄変" to arrayListOf("Energy -10\nSpeed +5\n(random) Stamina +5\n(random) Power +10\nアガッてきた！hint +1/+3"),
			),
			"Yukino Bijin" to mapOf(
				// R Guts トレセン学園
				"イカすライブのために" to arrayListOf(
					"Guts +10\nYukino Bijin bond +5",
					"Energy -10\nGuts +15\nYukino Bijin bond +5"),
				"\"シチースポット\"を目指して" to arrayListOf(
					"Energy -10\nMotivation +1\nGuts +10\nYukino Bijin bond +5",
					"コーナー加速○ hint +1\nYukino Bijin bond +5"),
				
				// SR Guts シチーガール入門＃
				"オーダートレーニング！" to arrayListOf("Motivation +1\nPower +5\nYukino Bijin bond +5"),
				"ナチュラル・シチーガール" to arrayListOf("Motivation +1\nIntelligence +25"),
				
				// SSR Intelligence ふるさと直送エール！
				"思い出ほわほわ、わんこそば" to arrayListOf(
					"Motivation +1\nYukino Bijin bond +5",
					"Maximum Energy +4\nYukino Bijin bond +5"),
				"学級委員長のすンげぇ特訓" to arrayListOf(
					"Motivation +1\nPower +5\nYukino Bijin bond +5",
					"Power +3\nGuts +3\nIntelligence +3\nYukino Bijin bond +5"),
				"あたし、勝ちたいンです！" to arrayListOf(
					"Motivation +1\nノンストップガール hint +1\nYukino Bijin bond +5",
					"Power +3\nGuts +3\nIntelligence +3\nノンストップガール hint +1\nYukino Bijin bond +5"),
			),
			"Zenno Rob Roy" to mapOf(
				// R Stamina トレセン学園
				"託された物語" to arrayListOf("Stamina +10\nIntelligence +10\nZenno Rob Roy bond +5", "中距離直線○ hint +1\nZenno Rob Roy bond +5"),
				"読書家あるある" to arrayListOf("Speed +5\nIntelligence +5\nZenno Rob Roy bond +5", "Energy +10\nPower +5\nZenno Rob Roy bond +5"),
				
				// SR Stamina おすすめ本、あります！
				"読書少女と魔法少女？" to arrayListOf("Stamina +5\nIntelligence +5\nZenno Rob Roy bond +5", "Energy +20\nPower +10\nZenno Rob Roy bond +5\nEvent chain ended"),
				"いつか、『物語』の主役に" to arrayListOf("Stamina +10\nIntelligence +10\n鋭い眼光 hint +1"),
				
				)
		)
	}
}