Feature: feature1

  @sanity @scenario3
  Scenario: Scenario4
    Given Scenario4
      | requestMethod | uri                 | pathParameters   | queryParameters | headerParameters        | requestFile       | responseFile       |
      | POST          | /api/order/{number} | reservation=5555 | id=1;user=1155  | accept=application/json | RequestFile4.json | ResponseFile4.json |

  @sanity @scenario4
  Scenario: Scenario4
    Given Scenario4
      | requestMethod | uri                 | pathParameters | queryParameters | headerParameters                                    | requestFile      | responseFile       |
      | POST          | /api/order/{number} | number=5555    | id=1;user=1155  | accept=application/xml;content-type=application/xml | RequestFile4.xml | ResponseFile4.json |
