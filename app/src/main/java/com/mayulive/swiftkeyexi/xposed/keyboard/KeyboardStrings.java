package com.mayulive.swiftkeyexi.xposed.keyboard;

/**
 * Created by Roughy on 9/19/2017.
 */

public class KeyboardStrings
{

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


}
