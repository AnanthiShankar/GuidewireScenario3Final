Feature: Holidays in LMS

Scenario: Displaying Holiday List as per Holiday type
Given User is on the EY LMS Page
When User Navigates to the Holiday List Page
Then Validate the public holiday count is  equal to or greater than “10”
And User is able to split the holiday details as per Holiday Type and print it as a report



#Scenario: Displaying Holiday List as per Holiday type
#Given User is on the EY LMS Page 
#When User Navigates to the Holiday List Page
#Then Validate the public holiday count is  equal to or greater than “10”
#And User is able to split the holiday details as per Holiday Type and print it as a report
#Then Validation message is printed

