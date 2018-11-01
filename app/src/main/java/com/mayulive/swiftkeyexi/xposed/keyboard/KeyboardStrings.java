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

	public static final String PUNCTUATION_STOCK_RULES =
			"{\n"+
					"    \"version\" : 2,\n"+
					"    \"lang\" : \"default\",\n"+
					"    \"punctuationRules\" : [\n"+
					"        {\n"+
					"            \"contextRules\": [\n"+
					"                {\n"+
					"                    \"ContextRule\": {\n"+
					"                        \"id\": [\n"+
					"                            \"(?i)\\\\b(www|http|ftp)$\"\n"+
					"                        ],\n"+
					"                        \"actions\": [\n"+
					"                            \"INS_FOCUS\",\n"+
					"                            \"DUMB_MODE\"\n"+
					"                        ]\n"+
					"                    }\n"+
					"                }\n"+
					"            ]\n"+
					"        },\n"+
					"        {\n"+
					"            \"charRules\": [\n"+
					"                {\n"+
					"                    \"CharRule\": {\n"+
					"                        \"id\": [\n"+
					"                            \"@\"\n"+
					"                        ],\n"+
					"                        \"actions\": [\n"+
					"                            \"INS_FOCUS\",\n"+
					"                            \"DUMB_MODE\"\n"+
					"                        ]\n"+
					"                    }\n"+
					"                },\n"+
					"                {\n"+
					"                    \"CharRule\": {\n"+
					"                        \"id\": [\n"+
					"                            \"_\"\n"+
					"                        ],\n"+
					"                        \"actions\": [\n"+
					"                            \"BACKSPACE\",\n"+
					"                            \"INS_FOCUS\"\n"+
					"                        ]\n"+
					"                    }\n"+
					"                },\n"+
					"                {\n"+
					"                    \"CharRule\": {\n"+
					"                        \"id\": [\n"+
					"                            \"…\",\n"+
					"                            \"”\"\n"+
					"                        ],\n"+
					"                        \"actions\": [\n"+
					"                            \"BACKSPACE\",\n"+
					"                            \"INS_FOCUS\",\n"+
					"                            \"INS_LANG_SPECIFIC_SPACE\"\n"+
					"                        ]\n"+
					"                    }\n"+
					"                },\n"+
					"                {\n"+
					"                    \"CharRule\": {\n"+
					"                        \"id\": [\n"+
					"                            \"-\"\n"+
					"                        ],\n"+
					"                        \"actions\": [],\n"+
					"                        \"contextRules\": [\n"+
					"                            {\n"+
					"                                \"ContextRule\": {\n"+
					"                                    \"id\": [\n"+
					"                                        \" $\"\n"+
					"                                    ],\n"+
					"                                    \"actions\": [\n"+
					"                                        \"INS_FOCUS\",\n"+
					"                                        \"INS_LANG_SPECIFIC_SPACE\"\n"+
					"                                    ]\n"+
					"                                }\n"+
					"                            }\n"+
					"                        ]\n"+
					"                    }\n"+
					"                },\n"+
					"                {\n"+
					"                    \"CharRule\": {\n"+
					"                        \"id\": [\n"+
					"                            \":\"\n"+
					"                        ],\n"+
					"                        \"actions\": [],\n"+
					"                        \"contextRules\": [\n"+
					"                            {\n"+
					"                                \"ContextRule\": {\n"+
					"                                    \"id\": [\n"+
					"                                        \"(?i)(file|ftp|http|https) $\"\n"+
					"                                    ],\n"+
					"                                    \"actions\": [\n"+
					"                                        \"BACKSPACE\",\n"+
					"                                        \"INS_FOCUS\",\n"+
					"                                        \"DUMB_MODE\"\n"+
					"                                    ]\n"+
					"                                }\n"+
					"                            }\n"+
					"                        ]\n"+
					"                    }\n"+
					"                },\n"+
					"                {\n"+
					"                    \"CharRule\": {\n"+
					"                        \"id\": [\n"+
					"                            \".\",\n"+
					"                            \",\",\n"+
					"                            \"!\",\n"+
					"                            \"?\",\n"+
					"                            \"}\",\n"+
					"                            \"]\",\n"+
					"                            \")\"\n"+
					"                        ],\n"+
					"                        \"actions\": [\n"+
					"                            \"BACKSPACE\",\n"+
					"                            \"INS_PREDICTION\",\n"+
					"                            \"INS_FOCUS\",\n"+
					"                            \"INS_LANG_SPECIFIC_SPACE\"\n"+
					"                        ],\n"+
					"                        \"contextRules\": [\n"+
					"                            {\n"+
					"                                \"CatRule\": {\n"+
					"                                    \"id\": [\n"+
					"                                      \"P*\"\n"+
					"                                    ],\n"+
					"                                    \"actions\": [\n"+
					"                                        \"BACKSPACE\",\n"+
					"                                        \"INS_FOCUS\",\n"+
					"                                        \"INS_LANG_SPECIFIC_SPACE\"\n"+
					"                                    ]\n"+
					"                                }\n"+
					"                            },\n"+
					"                            {\n"+
					"                                \"ContextRule\": {\n"+
					"                                    \"id\": [\n"+
					"                                        \" $\",\n"+
					"                                        \"\\u200B$\"\n"+
					"                                    ],\n"+
					"                                    \"actions\": [\n"+
					"                                        \"BACKSPACE\",\n"+
					"                                        \"INS_FOCUS\",\n"+
					"                                        \"INS_LANG_SPECIFIC_SPACE\"\n"+
					"                                    ]\n"+
					"                                }\n"+
					"                            }\n"+
					"                        ]\n"+
					"                    }\n"+
					"                },\n"+
					"                {\n"+
					"                    \"CharRule\": {\n"+
					"                        \"id\": [\n"+
					"                            \"\\\"\"\n"+
					"                        ],\n"+
					"                        \"actions\": [],\n"+
					"                        \"contextRules\": [\n"+
					"                            {\n"+
					"                                \"PairRule\": {\n"+
					"                                    \"id\": [\n"+
					"                                        \"\\\"\"\n"+
					"                                    ],\n"+
					"                                    \"state\": \"CLOSE\",\n"+
					"                                    \"actions\": [\n"+
					"                                        \"BACKSPACE\",\n"+
					"                                        \"INS_FOCUS\",\n"+
					"                                        \"INS_LANG_SPECIFIC_SPACE\"\n"+
					"                                    ]\n"+
					"                                }\n"+
					"                            }\n"+
					"                        ]\n"+
					"                    }\n"+
					"                },\n"+
					"                {\n"+
					"                    \"CharRule\": {\n"+
					"                        \"id\": [\n"+
					"                            \"'\"\n"+
					"                        ],\n"+
					"                        \"actions\": [],\n"+
					"                        \"contextRules\": [\n"+
					"                            {\n"+
					"                                \"PairRule\": {\n"+
					"                                    \"id\": [\n"+
					"                                        \"'\"\n"+
					"                                    ],\n"+
					"                                    \"state\": \"CLOSE\",\n"+
					"                                    \"actions\": [\n"+
					"                                        \"BACKSPACE\",\n"+
					"                                        \"INS_FOCUS\",\n"+
					"                                        \"INS_LANG_SPECIFIC_SPACE\"\n"+
					"                                    ]\n"+
					"                                }\n"+
					"                            }\n"+
					"                        ]\n"+
					"                    }\n"+
					"                },\n"+
					"                {\n"+
					"                    \"CharRule\": {\n"+
					"                        \"id\": [\n"+
					"                            \"&\"\n"+
					"                        ],\n"+
					"                        \"actions\": [],\n"+
					"                        \"contextRules\": [\n"+
					"                            {\n"+
					"                                \"ContextRule\": {\n"+
					"                                    \"id\": [\n"+
					"                                        \" $\"\n"+
					"                                    ],\n"+
					"                                    \"actions\": [\n"+
					"                                        \"INS_FOCUS\",\n"+
					"                                        \"INS_LANG_SPECIFIC_SPACE\"\n"+
					"                                    ]\n"+
					"                                }\n"+
					"                            }\n"+
					"                        ]\n"+
					"                    }\n"+
					"                }\n"+
					"            ]\n"+
					"        },\n"+
					"        {\n"+
					"            \"catRules\": [\n"+
					"                {\n"+
					"                    \"CatRule\": {\n"+
					"                        \"id\": [\n"+
					"                            \"Pe\",\n"+
					"                            \"Pf\"\n"+
					"                        ],\n"+
					"                        \"actions\": [\n"+
					"                            \"BACKSPACE\",\n"+
					"                            \"INS_FOCUS\",\n"+
					"                            \"INS_LANG_SPECIFIC_SPACE\"\n"+
					"                        ]\n"+
					"                    }\n"+
					"                }\n"+
					"            ]\n"+
					"        }\n"+
					"    ],\n"+
					"    \"defaultSpace\":\" \",\n"+
					"    \"encodingSeparator\":\" \",\n"+
					"    \"spacingRules\": [\n"+
					"        {\"term1\":\"\",\"term2\":\"-\",\"space\":\"\"}, {\"term1\":\"-\",\"term2\":\"\",\"space\":\"\"},\n"+
					"        {\"term1\":\"\",\"term2\":\"@\",\"space\":\"\"}, {\"term1\":\"@\",\"term2\":\"\",\"space\":\"\"},\n"+
					"        {\"term1\":\"\",\"term2\":\"_\",\"space\":\"\"}, {\"term1\":\"_\",\"term2\":\"\",\"space\":\"\"},\n"+
					"        {\"term1\":\"\",\"term2\":\"'\",\"space\":\"\"}, {\"term1\":\"'\",\"term2\":\"\",\"space\":\"\"},\n"+
					"        {\"term1\":\"\",\"term2\":\"'s\",\"space\":\"\"}\n"+
					"    ],\n"+
					"    \"sentenceSeparators\": [\".\", \"!\", \"?\"]\n"+
					"}\n";


	public static final String PUNCTUATION_MODIFIED_RULES =
	"{\n"+
		"    \"version\" : 2,\n"+
		"    \"lang\" : \"default\",\n"+
		"    \"punctuationRules\" : [\n"+
		"        {\n"+
		"            \"contextRules\": [\n"+
		"                {\n"+
		"                    \"ContextRule\": {\n"+
		"                        \"id\": [\n"+
		"                            \"(?i)\\\\b(www|http|ftp)$\"\n"+
		"                        ],\n"+
		"                        \"actions\": [\n"+
		"                            \"INS_FOCUS\",\n"+
		"                            \"DUMB_MODE\"\n"+
		"                        ]\n"+
		"                    }\n"+
		"                }\n"+
		"            ]\n"+
		"        },\n"+
		"        {\n"+
		"            \"charRules\": [\n"+
		"                {\n"+
		"                    \"CharRule\": {\n"+
		"                        \"id\": [\n"+
		"                            \"@\"\n"+
		"                        ],\n"+
		"                        \"actions\": [\n"+
		"                            \"INS_FOCUS\",\n"+
		"                            \"DUMB_MODE\"\n"+
		"                        ]\n"+
		"                    }\n"+
		"                },\n"+
		"                {\n"+
		"                    \"CharRule\": {\n"+
		"                        \"id\": [\n"+
		"                            \"_\"\n"+
		"                        ],\n"+
		"                        \"actions\": [\n"+
		"                            \"BACKSPACE\",\n"+
		"                            \"INS_FOCUS\"\n"+
		"                        ]\n"+
		"                    }\n"+
		"                },\n"+
		"                {\n"+
		"                    \"CharRule\": {\n"+
		"                        \"id\": [\n"+
		"                            \"…\",\n"+
		"                            \"”\"\n"+
		"                        ],\n"+
		"                        \"actions\": [\n"+
		"                            \"BACKSPACE\",\n"+
		"                            \"INS_FOCUS\"\n"+
	/*	"                            \"INS_LANG_SPECIFIC_SPACE\"\n"+ 			*/
		"                        ]\n"+
		"                    }\n"+
		"                },\n"+
		"                {\n"+
		"                    \"CharRule\": {\n"+
		"                        \"id\": [\n"+
		"                            \"-\"\n"+
		"                        ],\n"+
		"                        \"actions\": [],\n"+
		"                        \"contextRules\": [\n"+
		"                            {\n"+
		"                                \"ContextRule\": {\n"+
		"                                    \"id\": [\n"+
		"                                        \" $\"\n"+
		"                                    ],\n"+
		"                                    \"actions\": [\n"+
		"                                        \"INS_FOCUS\"\n"+
	/*	"                                        \"INS_LANG_SPECIFIC_SPACE\"\n"+ */
		"                                    ]\n"+
		"                                }\n"+
		"                            }\n"+
		"                        ]\n"+
		"                    }\n"+
		"                },\n"+
		"                {\n"+
		"                    \"CharRule\": {\n"+
		"                        \"id\": [\n"+
		"                            \":\"\n"+
		"                        ],\n"+
		"                        \"actions\": [],\n"+
		"                        \"contextRules\": [\n"+
		"                            {\n"+
		"                                \"ContextRule\": {\n"+
		"                                    \"id\": [\n"+
		"                                        \"(?i)(file|ftp|http|https) $\"\n"+
		"                                    ],\n"+
		"                                    \"actions\": [\n"+
		"                                        \"BACKSPACE\",\n"+
		"                                        \"INS_FOCUS\",\n"+
		"                                        \"DUMB_MODE\"\n"+
		"                                    ]\n"+
		"                                }\n"+
		"                            }\n"+
		"                        ]\n"+
		"                    }\n"+
		"                },\n"+
		"                {\n"+
		"                    \"CharRule\": {\n"+
		"                        \"id\": [\n"+
		"                            \".\",\n"+
		"                            \",\",\n"+
		"                            \"!\",\n"+
		"                            \"?\",\n"+
		"                            \"}\",\n"+
		"                            \"]\",\n"+
		"                            \")\"\n"+
		"                        ],\n"+
		"                        \"actions\": [\n"+
		"                            \"BACKSPACE\",\n"+
		"                            \"INS_PREDICTION\",\n"+
		"                            \"INS_FOCUS\"\n"+
	//	"                            \"INS_LANG_SPECIFIC_SPACE\"\n"+	//Space inserted after char
		"                        ],\n"+
		"                        \"contextRules\": [\n"+
		"                            {\n"+
		"                                \"CatRule\": {\n"+
		"                                    \"id\": [\n"+
		"                                      \"P*\"\n"+
		"                                    ],\n"+
		"                                    \"actions\": [\n"+
		"                                        \"BACKSPACE\",\n"+
		"                                        \"INS_FOCUS\"\n"+
	/*	"                                        \"INS_LANG_SPECIFIC_SPACE\"\n"+	*/	//This and below,
		"                                    ]\n"+										//Space inserted after character repeated twice
		"                                }\n"+
		"                            },\n"+
		"                            {\n"+
		"                                \"ContextRule\": {\n"+
		"                                    \"id\": [\n"+
		"                                        \" $\",\n"+
		"                                        \"\\u200B$\"\n"+
		"                                    ],\n"+
		"                                    \"actions\": [\n"+
		"                                        \"BACKSPACE\",\n"+
		"                                        \"INS_FOCUS\"\n"+
	/*	"                                        \"INS_LANG_SPECIFIC_SPACE\"\n"+	*/
		"                                    ]\n"+
		"                                }\n"+
		"                            }\n"+
		"                        ]\n"+
		"                    }\n"+
		"                },\n"+
		"                {\n"+
		"                    \"CharRule\": {\n"+
		"                        \"id\": [\n"+
		"                            \"\\\"\"\n"+
		"                        ],\n"+
		"                        \"actions\": [],\n"+
		"                        \"contextRules\": [\n"+
		"                            {\n"+
		"                                \"PairRule\": {\n"+
		"                                    \"id\": [\n"+
		"                                        \"\\\"\"\n"+
		"                                    ],\n"+
		"                                    \"state\": \"CLOSE\",\n"+
		"                                    \"actions\": [\n"+
		"                                        \"BACKSPACE\",\n"+
		"                                        \"INS_FOCUS\",\n"+
		"                                        \"INS_LANG_SPECIFIC_SPACE\"\n"+
		"                                    ]\n"+
		"                                }\n"+
		"                            }\n"+
		"                        ]\n"+
		"                    }\n"+
		"                },\n"+
		"                {\n"+
		"                    \"CharRule\": {\n"+
		"                        \"id\": [\n"+
		"                            \"'\"\n"+
		"                        ],\n"+
		"                        \"actions\": [],\n"+
		"                        \"contextRules\": [\n"+
		"                            {\n"+
		"                                \"PairRule\": {\n"+
		"                                    \"id\": [\n"+
		"                                        \"'\"\n"+
		"                                    ],\n"+
		"                                    \"state\": \"CLOSE\",\n"+
		"                                    \"actions\": [\n"+
		"                                        \"BACKSPACE\",\n"+
		"                                        \"INS_FOCUS\"\n"+
	/*	"                                        \"INS_LANG_SPECIFIC_SPACE\"\n"+  	*/
		"                                    ]\n"+
		"                                }\n"+
		"                            }\n"+
		"                        ]\n"+
		"                    }\n"+
		"                },\n"+
		"                {\n"+
		"                    \"CharRule\": {\n"+
		"                        \"id\": [\n"+
		"                            \"&\"\n"+
		"                        ],\n"+
		"                        \"actions\": [],\n"+
		"                        \"contextRules\": [\n"+
		"                            {\n"+
		"                                \"ContextRule\": {\n"+
		"                                    \"id\": [\n"+
		"                                        \" $\"\n"+
		"                                    ],\n"+
		"                                    \"actions\": [\n"+
		"                                        \"INS_FOCUS\"\n"+
	/*	"                                        \"INS_LANG_SPECIFIC_SPACE\"\n"+ */
		"                                    ]\n"+
		"                                }\n"+
		"                            }\n"+
		"                        ]\n"+
		"                    }\n"+
		"                }\n"+
		"            ]\n"+
		"        },\n"+
		"        {\n"+
		"            \"catRules\": [\n"+
		"                {\n"+
		"                    \"CatRule\": {\n"+
		"                        \"id\": [\n"+
		"                            \"Pe\",\n"+
		"                            \"Pf\"\n"+
		"                        ],\n"+
		"                        \"actions\": [\n"+
		"                            \"BACKSPACE\",\n"+
		"                            \"INS_FOCUS\",\n"+
		"                            \"INS_LANG_SPECIFIC_SPACE\"\n"+
		"                        ]\n"+
		"                    }\n"+
		"                }\n"+
		"            ]\n"+
		"        }\n"+
		"    ],\n"+
		"    \"defaultSpace\":\" \",\n"+
		"    \"encodingSeparator\":\" \",\n"+
		"    \"spacingRules\": [\n"+
		"        {\"term1\":\"\",\"term2\":\"-\",\"space\":\"\"}, {\"term1\":\"-\",\"term2\":\"\",\"space\":\"\"},\n"+
		"        {\"term1\":\"\",\"term2\":\"@\",\"space\":\"\"}, {\"term1\":\"@\",\"term2\":\"\",\"space\":\"\"},\n"+
		"        {\"term1\":\"\",\"term2\":\"_\",\"space\":\"\"}, {\"term1\":\"_\",\"term2\":\"\",\"space\":\"\"},\n"+
		"        {\"term1\":\"\",\"term2\":\"'\",\"space\":\"\"}, {\"term1\":\"'\",\"term2\":\"\",\"space\":\"\"},\n"+
		"        {\"term1\":\"\",\"term2\":\"'s\",\"space\":\"\"}\n"+
		"    ],\n"+
		"    \"sentenceSeparators\": [\".\", \"!\", \"?\"]\n"+
		"}\n";


}
