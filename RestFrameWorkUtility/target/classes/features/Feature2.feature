Feature: feature1

  @sanity @scenario1
  Scenario: Scenario3
    Given Scenario3
      | requestMethod | uri                      | pathParameters   | queryParameters | headerParameters        | requestFile       | responseFile       |
      | GET           | /api/order/{reservation} | reservation=9988 | id=1;user=2222  | accept=application/json | RequestFile3.json | ResponseFile3.json |

  @sanity @scenario2
  Scenario: Scenario4
    Given Scenario4
      | requestMethod | uri                 | pathParameters   | queryParameters | headerParameters        | requestFile       | responseFile       |
      | POST          | /api/order/{number} | reservation=6622 | id=1;user=1155  | accept=application/json | RequestFile4.json | ResponseFile4.json |
