# EventTrackerProject

## Overview


## How to Run
TODO: LINK TO DEPLOYED APP, LOGIN INFO IF NEEDED (Apr/5/2021)

## REST API
| HTTP Method | Resource URI       | Request Body | Returns                                               |
|-------------|--------------------|--------------|-------------------------------------------------------|
|GET          | api/purchases      |              | List&lt;Purchase&gt;                                  |
|GET          | api/purchase/{int} |              | Purchase by primary key                               |
|GET          | api/categories     |              | List&lt;Category&gt;                                  |
|GET          | api/purchases/{int}|              | List&lt;Purchase&gt; filtered by single category id   |
|POST         | api/purchases      | Purchase     | newly created Purchase                                |
|PUT          | api/purchase/{int} | Purchase     | edited Purchase                                       |
|DEL          | api/purchase/{int} |              | n/a                                                   |
