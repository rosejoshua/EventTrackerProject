# EventTrackerProject

## Overview
Simple MySQL Database and RESTful Gradle & SpringBoot Java project to be used as a back end for a spending tracker web-app. As it exists, there are two tables; one to be modified as a user logs purchase entries and another semi-permanent table for the spending categories. The user may make entries with a price & notes, choose the category from a drop-down, and a datetime will be automatically created upon submitting.This particular design assumes that a DBA will make any changes to the category table if needed and that the user will only edit fields in their purchase table.

## How to Run
Download all files, run MySQL script to create spendingdb database. Username and password are 'spendingdb'

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
