@CP-8917 @ACI @CREDITCARD @MOCKSERVER
Feature: ACI CREDITCARD VIA MOKSERVER

	Background:
		#@PRECOND_CP-8916
		Given Begin scenario for aci creditcard
#  Update experience action parameters with the following configuration :
#
#  "action_parameters": {
#    "CAPTURE": {
#      "MAX_RELAUNCH": "10",
#      "DELAY_BEFORE_RELAUNCH": "PT1S"
#    },
#    "VOID": {
#      "MAX_RELAUNCH": "10",
#      "DELAY_BEFORE_RELAUNCH": "PT1S"
#    }
#    "REFUND": {
#      "MAX_RELAUNCH": "10",
#      "DELAY_BEFORE_RELAUNCH": "PT1S"
#    }
#  }

	@TEST_CP-8915 @DeferredDebit
	Scenario Outline: [ACI MOCKSERVER] Débit différé - Capture timeout - OK côté partenaire - Refund OK
		Given use entity with DEFERRED_DEBIT
		When create a client session
			| amount |
			| 218    |
		And initialize session
			|  |
		And pre validate process payment
			| cardNumber | holder   | cvv   | method   |
			| <card>     | <holder> | <cvv> | <method> |
		And validate session
			|  |
		And I expect VERIFY result is AUTHORIZE SUCCESS
		Then I override response of action "CAPTURE" with timeout
		And I launch CAPTURE of TOTAL amount with expected state WAITING
		And I expect CAPTURE transaction status is "TIMEOUT"
		Then I expect GET_STATUS result is CAPTURE SUCCESS in the next 40 seconds
		Then I launch REFUND of TOTAL amount with expected state SUCCESS
		
			Examples:
				| card             | cvv | holder | method |
				| 4242424242424242 | 123 | tester | VIS    |
		
	@TEST_CP-8914 @DeferredDebit
	Scenario Outline: [ACI MOCKSERVER] Débit différé - Capture partiel 500 - KO côté partenaire
		Given use entity with DEFERRED_DEBIT
		When create a client session
			| amount |
			| 218    |
		And initialize session
			|  |
		And pre validate process payment
			| cardNumber | holder   | cvv   | method   |
			| <card>     | <holder> | <cvv> | <method> |
		And validate session
			|  |
		And I expect VERIFY result is AUTHORIZE SUCCESS
		
		Then I mock response of action "CAPTURE" with timeout
		And I launch CAPTURE of HALF amount with expected state WAITING
		And I expect CAPTURE transaction status is "TIMEOUT"
		Then I expect GET_STATUS result is CAPTURE PENDING
		Then I expect GET_STATUS result is CAPTURE SUCCESS in the next 40 seconds
		
			Examples:
				| card             | cvv | holder | method |
				| 4242424242424242 | 123 | tester | VIS    |
		
	@TEST_CP-8913 @DeferredDebit
	Scenario Outline: [ACI MOCKSERVER] Débit différé - Refund 500 - KO côté partenaire - GetStatus 500
		Given use entity with DEFERRED_DEBIT
		When create a client session
			| amount |
			| 218    |
		And initialize session
			|  |
		And pre validate process payment
			| cardNumber | holder   | cvv   | method   |
			| <card>     | <holder> | <cvv> | <method> |
		And validate session
			|  |
		And I expect VERIFY result is AUTHORIZE SUCCESS
		Then I launch CAPTURE of TOTAL amount with expected state SUCCESS
		Then I mock response of action "REFUND" with code 500
		And I launch REFUND of TOTAL amount with expected state WAITING
		And I expect REFUND transaction status is "PARTNER_SERVER_ERROR"
		
		Then I mock response of action "GET_PAYMENTS_BY_ORDER" with code 503
		And I expect GET_STATUS result is REFUND WAITING
		And I expect REFUND transaction status is "GET_STATUS_PARTNER_ERROR"
		Then I expect GET_STATUS result is REFUND PENDING
		Then I expect GET_STATUS result is REFUND SUCCESS in the next 40 seconds
		
			Examples:
				| card             | cvv | holder | method |
				| 4242424242424242 | 123 | tester | VIS    |
		
	@TEST_CP-8912 @DeferredDebit
	Scenario Outline: [ACI MOCKSERVER] Débit différé - Verify 5XX/timeout
		Given use entity with DEFERRED_DEBIT
		When create a client session
			| amount |
			| 218    |
		And initialize session
			|  |
		And pre validate process payment
			| cardNumber | holder   | cvv   | method   |
			| <card>     | <holder> | <cvv> | <method> |
		And validate session
			|  |
		
		Then I mock response of action "VERIFY" with timeout
		And I expect VERIFY result is APPLY SUCCESS
		And I expect APPLY transaction status is "VERIFY_TIMEOUT"
		
		Then I override response of action "VERIFY" with code 502
		And I expect VERIFY result is APPLY SUCCESS
		And I expect APPLY transaction status is "VERIFY_PARTNER_ERROR"
		
		And I expect VERIFY result is AUTHORIZE SUCCESS
		
			Examples:
				| card             | cvv | holder | method |
				| 4242424242424242 | 123 | tester | VIS    |
		
	@TEST_CP-8911 @DeferredDebit
	Scenario Outline: [ACI MOCKSERVER] Débit différé - Void 5XX - KO côté partenaire
		Given use entity with DEFERRED_DEBIT
		When create a client session
			| amount |
			| 218    |
		And initialize session
			|  |
		And pre validate process payment
			| cardNumber | holder   | cvv   | method   |
			| <card>     | <holder> | <cvv> | <method> |
		And validate session
			|  |
		And I expect VERIFY result is AUTHORIZE SUCCESS
		
		Then I mock response of action "VOID" with code 500
		And I launch VOID of TOTAL amount with expected state WAITING
		And I expect VOID transaction status is "PARTNER_SERVER_ERROR"
		Then I expect GET_STATUS result is VOID PENDING
		Then I expect GET_STATUS result is VOID SUCCESS in the next 40 seconds
		
			Examples:
				| card             | cvv | holder | method |
				| 4242424242424242 | 123 | tester | VIS    |
		
	@TEST_CP-8910 @DeferredDebit
	Scenario Outline: [ACI MOCKSERVER] Débit différé - Refund 503 - OK côté partenaire
		Given use entity with DEFERRED_DEBIT
		When create a client session
			| amount |
			| 218    |
		And initialize session
			|  |
		And pre validate process payment
			| cardNumber | holder   | cvv   | method   |
			| <card>     | <holder> | <cvv> | <method> |
		And validate session
			|  |
		And I expect VERIFY result is AUTHORIZE SUCCESS
		Then I launch CAPTURE of TOTAL amount with expected state SUCCESS
		Then I override response of action "REFUND" with code 503
		And I launch REFUND of TOTAL amount with expected state WAITING
		And I expect REFUND transaction status is "PARTNER_UNAVAILABLE"
		And I expect GET_STATUS result is REFUND SUCCESS
		
			Examples:
				| card             | cvv | holder | method |
				| 4242424242424242 | 123 | tester | VIS    |
		
	@TEST_CP-8909 @DeferredDebit
	Scenario Outline: [ACI MOCKSERVER] Débit différé - Capture timeout - OK côté partenaire - GetStatus 500
		Given use entity with DEFERRED_DEBIT
		When create a client session
			| amount |
			| 218    |
		And initialize session
			|  |
		And pre validate process payment
			| cardNumber | holder   | cvv   | method   |
			| <card>     | <holder> | <cvv> | <method> |
		And validate session
			|  |
		And I expect VERIFY result is AUTHORIZE SUCCESS
		Then I override response of action "CAPTURE" with timeout
		And I launch CAPTURE of TOTAL amount with expected state WAITING
		And I expect CAPTURE transaction status is "TIMEOUT"
		
		Then I mock response of action "GET_PAYMENTS_BY_ORDER" with code 503
		And I expect GET_STATUS result is CAPTURE WAITING
		And I expect CAPTURE transaction status is "GET_STATUS_PARTNER_ERROR"
		
		Then I mock response of action "GET_PAYMENTS_BY_ORDER" with timeout
		And I expect GET_STATUS result is CAPTURE WAITING
		And I expect CAPTURE transaction status is "GET_STATUS_TIMEOUT"
		
		Then I expect GET_STATUS result is CAPTURE SUCCESS in the next 40 seconds
		Then I launch REFUND of TOTAL amount with expected state SUCCESS
		
			Examples:
				| card             | cvv | holder | method |
				| 4242424242424242 | 123 | tester | VIS    |
		
	@TEST_CP-8908 @DeferredDebit
	Scenario Outline: [ACI MOCKSERVER] Débit différé - Void timeout - OK côté partenaire
		Given use entity with DEFERRED_DEBIT
		When create a client session
			| amount |
			| 218    |
		And initialize session
			|  |
		And pre validate process payment
			| cardNumber | holder   | cvv   | method   |
			| <card>     | <holder> | <cvv> | <method> |
		And validate session
			|  |
		And I expect VERIFY result is AUTHORIZE SUCCESS
		
		Then I override response of action "VOID" with timeout
		And I launch VOID of TOTAL amount with expected state WAITING
		And I expect VOID transaction status is "TIMEOUT"
		And I wait 5 seconds
		Then I expect GET_STATUS result is VOID SUCCESS
		
			Examples:
				| card             | cvv | holder | method |
				| 4242424242424242 | 123 | tester | VIS    |
		
	@TEST_CP-8907 @DeferredDebit
	Scenario Outline: [ACI MOCKSERVER] Débit différé - Refund 5XX/timeout - KO côté partenaire
		Given use entity with DEFERRED_DEBIT
		When create a client session
			| amount |
			| 218    |
		And initialize session
			|  |
		And pre validate process payment
			| cardNumber | holder   | cvv   | method   |
			| <card>     | <holder> | <cvv> | <method> |
		And validate session
			|  |
		And I expect VERIFY result is AUTHORIZE SUCCESS
		Then I launch CAPTURE of TOTAL amount with expected state SUCCESS
		
		Then I mock response of action "REFUND" with code 500
		And I launch REFUND of TOTAL amount with expected state WAITING
		And I expect REFUND transaction status is "PARTNER_SERVER_ERROR"
		Then I expect GET_STATUS result is REFUND PENDING
		
		Then I mock response of action "REFUND" with code 502
		And I launch REFUND of TOTAL amount with expected state WAITING
		And I expect REFUND transaction status is "PARTNER_BAD_GATEWAY"
		Then I expect GET_STATUS result is REFUND PENDING
		
		Then I mock response of action "REFUND" with code 503
		And I launch REFUND of TOTAL amount with expected state WAITING
		And I expect REFUND transaction status is "PARTNER_UNAVAILABLE"
		Then I expect GET_STATUS result is REFUND PENDING
		
		Then I mock response of action "REFUND" with timeout
		And I launch REFUND of TOTAL amount with expected state WAITING
		And I expect REFUND transaction status is "TIMEOUT"
		Then I expect GET_STATUS result is REFUND PENDING
		
			Examples:
				| card             | cvv | holder | method |
				| 4242424242424242 | 123 | tester | VIS    |
		
	@TEST_CP-8906 @DeferredDebit
	Scenario Outline: [ACI MOCKSERVER] Débit différé - Refund 503 - KO côté partenaire
		Given use entity with DEFERRED_DEBIT
		When create a client session
			| amount |
			| 218    |
		And initialize session
			|  |
		And pre validate process payment
			| cardNumber | holder   | cvv   | method   |
			| <card>     | <holder> | <cvv> | <method> |
		And validate session
			|  |
		And I expect VERIFY result is AUTHORIZE SUCCESS
		Then I launch CAPTURE of TOTAL amount with expected state SUCCESS
		
		Then I mock response of action "REFUND" with code 500
		And I launch REFUND of TOTAL amount with expected state WAITING
		And I expect REFUND transaction status is "PARTNER_SERVER_ERROR"
		Then I expect GET_STATUS result is REFUND PENDING
		Then I expect GET_STATUS result is REFUND SUCCESS in the next 40 seconds
		
			Examples:
				| card             | cvv | holder | method |
				| 4242424242424242 | 123 | tester | VIS    |
		
	@TEST_CP-8905 @DeferredDebit
	Scenario Outline: [ACI MOCKSERVER] Débit différé - Capture 5XX/timeout - KO côté partenaire
		Given use entity with DEFERRED_DEBIT
		When create a client session
			| amount |
			| 218    |
		And initialize session
			|  |
		And pre validate process payment
			| cardNumber | holder   | cvv   | method   |
			| <card>     | <holder> | <cvv> | <method> |
		And validate session
			|  |
		And I expect VERIFY result is AUTHORIZE SUCCESS
		
		Then I mock response of action "CAPTURE" with code 500
		And I launch CAPTURE of TOTAL amount with expected state WAITING
		And I expect CAPTURE transaction status is "PARTNER_SERVER_ERROR"
		And I expect GET_STATUS result is CAPTURE PENDING
		
		Then I mock response of action "CAPTURE" with code 503
		And I launch CAPTURE of TOTAL amount with expected state WAITING
		And I expect CAPTURE transaction status is "PARTNER_UNAVAILABLE"
		And I expect GET_STATUS result is CAPTURE PENDING
		
		Then I mock response of action "CAPTURE" with timeout
		And I launch CAPTURE of TOTAL amount with expected state WAITING
		And I expect CAPTURE transaction status is "TIMEOUT"
		And I expect GET_STATUS result is CAPTURE PENDING
		
			Examples:
				| card             | cvv | holder | method |
				| 4242424242424242 | 123 | tester | VIS    |
		
	@TEST_CP-8904 @DeferredDebit
	Scenario Outline: [ACI MOCKSERVER] Débit différé - Capture 502 - KO côté partenaire - Refund OK
		Given use entity with DEFERRED_DEBIT
		When create a client session
			| amount |
			| 218    |
		And initialize session
			|  |
		And pre validate process payment
			| cardNumber | holder   | cvv   | method   |
			| <card>     | <holder> | <cvv> | <method> |
		And validate session
			|  |
		And I expect VERIFY result is AUTHORIZE SUCCESS
		
		Then I mock response of action "CAPTURE" with code 502
		And I launch CAPTURE of TOTAL amount with expected state WAITING
		And I expect CAPTURE transaction status is "PARTNER_BAD_GATEWAY"
		Then I expect GET_STATUS result is CAPTURE PENDING
		Then I expect GET_STATUS result is CAPTURE SUCCESS in the next 40 seconds
		Then I launch REFUND of TOTAL amount with expected state SUCCESS
		
			Examples:
				| card             | cvv | holder | method |
				| 4242424242424242 | 123 | tester | VIS    |
		
