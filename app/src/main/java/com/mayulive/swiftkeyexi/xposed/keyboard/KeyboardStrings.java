package com.mayulive.swiftkeyexi.xposed.keyboard;

/**
 * Created by Roughy on 9/19/2017.
 */

public class KeyboardStrings
{

	public static final String[] ALL_COUNTRY_CODES = new String[]{
			"AD",
			"AE",
			"AF",
			"AG",
			"AI",
			"AL",
			"AM",
			"AO",
			"AQ",
			"AR",
			"AS",
			"AT",
			"AU",
			"AW",
			"AX",
			"AZ",
			"BA",
			"BB",
			"BD",
			"BE",
			"BF",
			"BG",
			"BH",
			"BI",
			"BJ",
			"BL",
			"BM",
			"BN",
			"BO",
			"BQ",
			"BR",
			"BS",
			"BT",
			"BV",
			"BW",
			"BY",
			"BZ",
			"CA",
			"CC",
			"CD",
			"CF",
			"CG",
			"CH",
			"CI",
			"CK",
			"CL",
			"CM",
			"CN",
			"CO",
			"CR",
			"CU",
			"CV",
			"CW",
			"CX",
			"CY",
			"CZ",
			"DE",
			"DJ",
			"DK",
			"DM",
			"DO",
			"DZ",
			"EC",
			"EE",
			"EG",
			"EH",
			"ER",
			"ES",
			"ET",
			"FI",
			"FJ",
			"FK",
			"FM",
			"FO",
			"FR",
			"GA",
			"GB",
			"GD",
			"GE",
			"GF",
			"GG",
			"GH",
			"GI",
			"GL",
			"GM",
			"GN",
			"GP",
			"GQ",
			"GR",
			"GS",
			"GT",
			"GU",
			"GW",
			"GY",
			"HK",
			"HM",
			"HN",
			"HR",
			"HT",
			"HU",
			"ID",
			"IE",
			"IL",
			"IM",
			"IN",
			"IO",
			"IQ",
			"IR",
			"IS",
			"IT",
			"JE",
			"JM",
			"JO",
			"JP",
			"KE",
			"KG",
			"KH",
			"KI",
			"KM",
			"KN",
			"KP",
			"KR",
			"KW",
			"KY",
			"KZ",
			"LA",
			"LB",
			"LC",
			"LI",
			"LK",
			"LR",
			"LS",
			"LT",
			"LU",
			"LV",
			"LY",
			"MA",
			"MC",
			"MD",
			"ME",
			"MF",
			"MG",
			"MH",
			"MK",
			"ML",
			"MM",
			"MN",
			"MO",
			"MP",
			"MQ",
			"MR",
			"MS",
			"MT",
			"MU",
			"MV",
			"MW",
			"MX",
			"MY",
			"MZ",
			"NA",
			"NC",
			"NE",
			"NF",
			"NG",
			"NI",
			"NL",
			"NO",
			"NP",
			"NR",
			"NU",
			"NZ",
			"OM",
			"PA",
			"PE",
			"PF",
			"PG",
			"PH",
			"PK",
			"PL",
			"PM",
			"PN",
			"PR",
			"PS",
			"PT",
			"PW",
			"PY",
			"QA",
			"RE",
			"RO",
			"RS",
			"RU",
			"RW",
			"SA",
			"SB",
			"SC",
			"SD",
			"SE",
			"SG",
			"SH",
			"SI",
			"SJ",
			"SK",
			"SL",
			"SM",
			"SN",
			"SO",
			"SR",
			"SS",
			"ST",
			"SV",
			"SX",
			"SY",
			"SZ",
			"TC",
			"TD",
			"TF",
			"TG",
			"TH",
			"TJ",
			"TK",
			"TL",
			"TM",
			"TN",
			"TO",
			"TR",
			"TT",
			"TV",
			"TW",
			"TZ",
			"UA",
			"UG",
			"UM",
			"US",
			"UY",
			"UZ",
			"VA",
			"VC",
			"VE",
			"VG",
			"VI",
			"VN",
			"VU",
			"WF",
			"WS",
			"YE",
			"YT",
			"ZA",
			"ZM",
			"ZW"
	};


	//Don't overthink the rules. The Context rules are evidently just regex, with some specific
	//referring to Predictions and such.
	//As of writing we just remove the INS_LANG_SPECIFIC_SPACE action from a rule pertaining to punctuation.

	public static String getPunctuationRules( boolean noSpaceAfterPunctuation, boolean noRemoveSpaceBeforePunctuation )
	{
		StringBuilder builder = new StringBuilder();

		builder.append("{\n");
		builder.append("    \"version\" : 2,\n");
		builder.append("    \"lang\" : \"default\",\n");
		builder.append("    \"punctuationRules\" : [\n");
		builder.append("        {\n");
		builder.append("            \"contextRules\": [\n");
		builder.append("                {\n");
		builder.append("                    \"ContextRule\": {\n");
		builder.append("                        \"id\": [\n");
		builder.append("                            \"(?i)\\\\b(www|http|ftp)$\"\n");
		builder.append("                        ],\n");
		builder.append("                        \"actions\": [\n");
		builder.append("                            \"INS_FOCUS\",\n");
		builder.append("                            \"DUMB_MODE\"\n");
		builder.append("                        ]\n");
		builder.append("                    }\n");
		builder.append("                }\n");
		builder.append("            ]\n");
		builder.append("        },\n");
		builder.append("        {\n");
		builder.append("            \"charRules\": [\n");

		builder.append("                {\n");
		builder.append("                    \"CharRule\": {\n");
		builder.append("                        \"id\": [\n");
		builder.append("                            \"\\uE1EA\"\n");
		builder.append("                        ],\n");
		builder.append("                        \"actions\": [\n");
		builder.append("                            \"INS_PREDICTION\",\n");
		builder.append("                            \"INS_LANG_SPECIFIC_SPACE\"\n");


		builder.append("                        ],\n");
		builder.append("                        \"PredictionRules\": [\n");
		builder.append("                            {\n");
		builder.append("                                \"PredictionRule\": {\n");
		builder.append("                                    \"id\": [\n");
		builder.append("                                        \".\",\n");
		builder.append("                                        \",\",\n");
		builder.append("                                        \"!\",\n");
		builder.append("                                        \"?\"\n");
		builder.append("                                    ],\n");
		builder.append("                                    \"actions\": [\n");
		builder.append("                                        \"BACKSPACE\",\n");

		if (noSpaceAfterPunctuation)
		{
			builder.append("                                        \"INS_PREDICTION\"\n");
		}
		else
		{
			builder.append("                                        \"INS_PREDICTION\",\n");
			builder.append("                                        \"INS_LANG_SPECIFIC_SPACE\"\n");
		}


		builder.append("                                    ]\n");
		builder.append("                                }\n");
		builder.append("                            }\n");
		builder.append("                        ]\n");
		builder.append("                    }\n");
		builder.append("                },\n");

		builder.append("                {\n");
		builder.append("                    \"CharRule\": {\n");
		builder.append("                        \"id\": [\n");
		builder.append("                            \"@\"\n");
		builder.append("                        ],\n");
		builder.append("                        \"actions\": [\n");
		builder.append("                            \"INS_FOCUS\",\n");
		builder.append("                            \"DUMB_MODE\"\n");
		builder.append("                        ]\n");
		builder.append("                    }\n");
		builder.append("                },\n");
		builder.append("                {\n");
		builder.append("                    \"CharRule\": {\n");
		builder.append("                        \"id\": [\n");
		builder.append("                            \"…\",\n");
		builder.append("                            \"”\"\n");
		builder.append("                        ],\n");
		builder.append("                        \"actions\": [\n");
		builder.append("                            \"BACKSPACE\",\n");


		if (noSpaceAfterPunctuation)
		{
			builder.append("                            \"INS_FOCUS\"\n");
		}
		else
		{
			builder.append("                            \"INS_FOCUS\",\n");
			builder.append("                            \"INS_LANG_SPECIFIC_SPACE\"\n");
		}

		builder.append("                        ]\n");
		builder.append("                    }\n");
		builder.append("                },\n");
		builder.append("                {\n");
		builder.append("                    \"CharRule\": {\n");
		builder.append("                        \"id\": [\n");
		builder.append("                            \"-\"\n");
		builder.append("                        ],\n");
		builder.append("                        \"actions\": [],\n");
		builder.append("                        \"contextRules\": [\n");
		builder.append("                            {\n");
		builder.append("                                \"ContextRule\": {\n");
		builder.append("                                    \"id\": [\n");
		builder.append("                                        \" $\"\n");
		builder.append("                                    ],\n");
		builder.append("                                    \"actions\": [\n");

		if (noSpaceAfterPunctuation)
		{
			builder.append("                                        \"INS_FOCUS\"\n");
		}
		else
		{
			builder.append("                                        \"INS_FOCUS\",\n");
			builder.append("                                        \"INS_LANG_SPECIFIC_SPACE\"\n");
		}

		builder.append("                                    ]\n");
		builder.append("                                }\n");
		builder.append("                            }\n");
		builder.append("                        ]\n");
		builder.append("                    }\n");
		builder.append("                },\n");
		builder.append("                {\n");
		builder.append("                    \"CharRule\": {\n");
		builder.append("                        \"id\": [\n");
		builder.append("                            \":\"\n");
		builder.append("                        ],\n");
		builder.append("                        \"actions\": [],\n");
		builder.append("                        \"contextRules\": [\n");
		builder.append("                            {\n");
		builder.append("                                \"ContextRule\": {\n");
		builder.append("                                    \"id\": [\n");
		builder.append("                                        \"(?i)(file|ftp|http|https) $\"\n");
		builder.append("                                    ],\n");
		builder.append("                                    \"actions\": [\n");
		builder.append("                                        \"BACKSPACE\",\n");
		builder.append("                                        \"INS_FOCUS\",\n");
		builder.append("                                        \"DUMB_MODE\"\n");
		builder.append("                                    ]\n");
		builder.append("                                }\n");
		builder.append("                            }\n");
		builder.append("                        ]\n");
		builder.append("                    }\n");
		builder.append("                },\n");









		builder.append("                {\n");
		builder.append("                    \"CharRule\": {\n");
		builder.append("                        \"id\": [\n");
		builder.append("                            \".\",\n");
		builder.append("                            \",\",\n");
		builder.append("                            \"!\",\n");
		builder.append("                            \"?\",\n");
		builder.append("                            \"}\",\n");
		builder.append("                            \"]\",\n");
		builder.append("                            \")\"\n");
		builder.append("                        ],\n");
		builder.append("                        \"actions\": [\n");		// Default
		builder.append("                            \"BACKSPACE\",\n");
		builder.append("                            \"INS_PREDICTION\",\n");

		if (noSpaceAfterPunctuation)
		{
			builder.append("                            \"INS_FOCUS\"\n");
		}
		else
		{
			builder.append("                            \"INS_FOCUS\",\n");
			builder.append("                            \"INS_LANG_SPECIFIC_SPACE\"\n");
		}


		builder.append("                        ],\n");
		builder.append("                        \"contextRules\": [\n");
		builder.append("                            {\n");						// After prediction
		builder.append("                                \"CatRule\": {\n");
		builder.append("                                    \"id\": [\n");
		builder.append("                                      \"P*\"\n");
		builder.append("                                    ],\n");
		builder.append("                                    \"actions\": [\n");


		if (!noRemoveSpaceBeforePunctuation)
		{
			builder.append("                                        \"BACKSPACE\",\n");
		}


		if (noSpaceAfterPunctuation)
		{
			builder.append("                                        \"INS_FOCUS\"\n");
		}
		else
		{
			builder.append("                                        \"INS_FOCUS\",\n");
			builder.append("                                        \"INS_LANG_SPECIFIC_SPACE\"\n");
		}


		builder.append("                                    ]\n");
		builder.append("                                }\n");


		builder.append("                            },\n");
		builder.append("                            {\n");						// after space?
		builder.append("                                \"ContextRule\": {\n");
		builder.append("                                    \"id\": [\n");
		builder.append("                                        \" $\",\n");
		builder.append("                                        \"\\u200B$\"\n");
		builder.append("                                    ],\n");
		builder.append("                                    \"actions\": [\n");

		if (!noRemoveSpaceBeforePunctuation)
		{
			builder.append("                                        \"BACKSPACE\",\n");
		}



		if (noSpaceAfterPunctuation)
		{
			builder.append("                                        \"INS_FOCUS\"\n");
		}
		else
		{
			builder.append("                                        \"INS_FOCUS\",\n");
			builder.append("                                        \"INS_LANG_SPECIFIC_SPACE\"\n");
		}

		builder.append("                                    ]\n");
		builder.append("                                }\n");
		builder.append("                            }\n");







		builder.append("                        ]\n");
		builder.append("                    }\n");
		builder.append("                },\n");







		builder.append("                {\n");
		builder.append("                    \"CharRule\": {\n");
		builder.append("                        \"id\": [\n");
		builder.append("                            \"\\\"\"\n");
		builder.append("                        ],\n");
		builder.append("                        \"actions\": [],\n");
		builder.append("                        \"contextRules\": [\n");
		builder.append("                            {\n");
		builder.append("                                \"PairRule\": {\n");
		builder.append("                                    \"id\": [\n");
		builder.append("                                        \"\\\"\"\n");
		builder.append("                                    ],\n");
		builder.append("                                    \"state\": \"CLOSE\",\n");
		builder.append("                                    \"actions\": [\n");
		builder.append("                                        \"BACKSPACE\",\n");
		builder.append("                                        \"INS_PREDICTION\",\n");

		if (noSpaceAfterPunctuation)
		{
			builder.append("                                        \"INS_FOCUS\"\n");
		}
		else
		{
			builder.append("                                        \"INS_FOCUS\",\n");
			builder.append("                                        \"INS_LANG_SPECIFIC_SPACE\"\n");
		}

		builder.append("                                    ],\n");
		builder.append("                                    \"contextRules\": [\n");
		builder.append("                                        {\n");
		builder.append("                                            \"CatRule\": {\n");
		builder.append("                                                \"id\": [\n");
		builder.append("                                                    \"P*\"\n");
		builder.append("                                                ],\n");
		builder.append("                                                \"actions\": [\n");
		builder.append("                                                    \"BACKSPACE\",\n");
		builder.append("                                                    \"INS_FOCUS\",\n");
		builder.append("                                                    \"INS_LANG_SPECIFIC_SPACE\"\n");
		builder.append("                                                ]\n");
		builder.append("                                            }\n");
		builder.append("                                        },\n");
		builder.append("                                        {\n");
		builder.append("                                            \"ContextRule\": {\n");
		builder.append("                                                \"id\": [\n");
		builder.append("                                                    \" $\",\n");
		builder.append("                                                    \"\\u200B$\"\n");
		builder.append("                                                ],\n");
		builder.append("                                                \"actions\": [\n");
		builder.append("                                                    \"BACKSPACE\",\n");
		builder.append("                                                    \"INS_FOCUS\",\n");
		builder.append("                                                    \"INS_LANG_SPECIFIC_SPACE\"\n");
		builder.append("                                                ]\n");
		builder.append("                                            }\n");
		builder.append("                                        }\n");
		builder.append("                                    ]\n");
		builder.append("                                }\n");
		builder.append("                            }\n");
		builder.append("                        ]\n");
		builder.append("                    }\n");
		builder.append("                },\n");
		builder.append("                {\n");
		builder.append("                    \"CharRule\": {\n");
		builder.append("                        \"id\": [\n");
		builder.append("                            \"'\"\n");
		builder.append("                        ],\n");
		builder.append("                        \"actions\": [],\n");
		builder.append("                        \"contextRules\": [\n");
		builder.append("                            {\n");
		builder.append("                                \"PairRule\": {\n");
		builder.append("                                    \"id\": [\n");
		builder.append("                                        \"'\"\n");
		builder.append("                                    ],\n");
		builder.append("                                    \"state\": \"CLOSE\",\n");
		builder.append("                                    \"actions\": [\n");
		builder.append("                                        \"BACKSPACE\",\n");
		builder.append("                                        \"INS_FOCUS\",\n");
		builder.append("                                        \"INS_LANG_SPECIFIC_SPACE\"\n");
		builder.append("                                    ]\n");
		builder.append("                                }\n");
		builder.append("                            }\n");
		builder.append("                        ]\n");
		builder.append("                    }\n");
		builder.append("                },\n");

		if (!noSpaceAfterPunctuation)
		{
			builder.append("                {\n");
			builder.append("                    \"CharRule\": {\n");
			builder.append("                        \"id\": [\n");
			builder.append("                            \"&\"\n");
			builder.append("                        ],\n");
			builder.append("                        \"actions\": [],\n");
			builder.append("                        \"contextRules\": [\n");
			builder.append("                            {\n");
			builder.append("                                \"ContextRule\": {\n");
			builder.append("                                    \"id\": [\n");
			builder.append("                                        \" $\"\n");
			builder.append("                                    ],\n");
			builder.append("                                    \"actions\": [\n");
			builder.append("                                        \"INS_FOCUS\",\n");
			builder.append("                                        \"INS_LANG_SPECIFIC_SPACE\"\n");
			builder.append("                                    ]\n");
			builder.append("                                }\n");
			builder.append("                            }\n");
			builder.append("                        ]\n");
			builder.append("                    }\n");
			builder.append("                },\n");
		}

		builder.append("                {\n");
		builder.append("                    \"CharRule\": {\n");
		builder.append("                         \"_comment\": [\n");
		builder.append("                            \"U+2019\",\n");
		builder.append("                            \"We want to keep the RIGHT SINGLE QUOTATION MARK\",\n");
		builder.append("                            \"from adding a space (like other Pf do)\",\n");
		builder.append("                            \"cf LP-881\"\n");
		builder.append("                        ],\n");
		builder.append("                        \"id\": [\n");
		builder.append("                            \"’\"\n");
		builder.append("                        ],\n");
		builder.append("                        \"actions\": [\n");
		builder.append("                            \"INS_FOCUS\"\n");
		builder.append("                        ]\n");
		builder.append("                    }\n");
		builder.append("                }\n");
		builder.append("            ]\n");
		builder.append("        },\n");
		builder.append("        {\n");
		builder.append("            \"catRules\": [\n");
		builder.append("                {\n");
		builder.append("                    \"CatRule\": {\n");
		builder.append("                        \"id\": [\n");
		builder.append("                            \"Pe\",\n");
		builder.append("                            \"Pf\"\n");
		builder.append("                        ],\n");
		builder.append("                        \"actions\": [\n");
		builder.append("                            \"BACKSPACE\",\n");
		builder.append("                            \"INS_FOCUS\",\n");
		builder.append("                            \"INS_LANG_SPECIFIC_SPACE\"\n");
		builder.append("                        ]\n");
		builder.append("                    }\n");
		builder.append("                }\n");
		builder.append("            ]\n");
		builder.append("        }\n");
		builder.append("    ],\n");
		builder.append("    \"defaultSpace\":\" \",\n");
		builder.append("    \"encodingSeparator\":\" \",\n");
		builder.append("    \"spacingRules\": [\n");
		builder.append("        {\"term1\":\"\",\"term2\":\"-\",\"space\":\"\"}, {\"term1\":\"-\",\"term2\":\"\",\"space\":\"\"},\n");
		builder.append("        {\"term1\":\"\",\"term2\":\"@\",\"space\":\"\"}, {\"term1\":\"@\",\"term2\":\"\",\"space\":\"\"},\n");
		builder.append("        {\"term1\":\"\",\"term2\":\"_\",\"space\":\"\"}, {\"term1\":\"_\",\"term2\":\"\",\"space\":\"\"},\n");
		builder.append("        {\"term1\":\"\",\"term2\":\"'\",\"space\":\"\"}, {\"term1\":\"'\",\"term2\":\"\",\"space\":\"\"},\n");
		builder.append("        {\"term1\":\"\",\"term2\":\"'s\",\"space\":\"\"}\n");
		builder.append("    ],\n");
		builder.append("    \"sentenceSeparators\": [\".\", \"!\", \"?\"],\n");
		builder.append("    \"wordSeparators\" : [\"-\"]\n");
		builder.append("}\n");

		return builder.toString();


	}
}
