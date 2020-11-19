# Text search Rest API example application





## Run the app

    gradle run

## Run the tests

    gradle test

# REST API

The REST API to the example app is described below.

## Search in file
### Request

`GET /search?filePath=data/lorem-ipsum.txt&text=Lorem/`

    curl --location --request GET 'localhost:8080/search?filePath=data/lorem-ipsum.txt&text=Lorem'

### Response

    HTTP/1.1 200 OK
    Status: 200 OK
    Connection: close
    Content-Type: application/json
    Content-Length: 2

    [
        {
            "responseLine": "Mauris venenatis posuere pellentesque. Phasellus non imperdiet purus. Donec sagittis luctus erat eu consequat. Vestibulum orci lectus, ultricies in ante vel, hendrerit tempus nibh. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris eros risus, placerat sit amet neque sit amet, tempus rutrum velit. Praesent eget quam consequat, rhoncus quam at, rhoncus nibh. Nam non ligula et lectus varius aliquet. Phasellus aliquet vulputate ornare. Integer nisl orci, commodo a rutrum ac, placerat id arcu. Curabitur ullamcorper laoreet mi vitae pulvinar."
        }
    ]

## Search in non existed file

### Request

`GET /search?filePath=data/random.file&text=Lorem/`

    curl --location --request GET 'localhost:8080/search?filePath=random.file&text=Lorem'

### Response

    Status: 400 Bad Request
    Connection: close
    Content-Type: application/json
    Content-Length: 35

    {
        "timestamp": "2020-11-19T09:55:16.363+00:00",
        "path": "/search",
        "status": 400,
        "error": "Bad Request",
        "message": "search.filePath: File with this path not exist",
        "requestId": "27e466eb-1"
    }



## Get file search history

### Request

`GET search/history?direction=ASC`

    curl --location --request GET 'localhost:8080/search/history?direction=DESC'

### Response

      HTTP/1.1 200 OK
      Status: 200 OK
      Connection: close
      Content-Type: application/json
      Content-Length: 2
  
      [
          {
              "searchText": "Mauris venenati",
              "filePath": "data/lorem-ipsum.txt",
              "searchDate": "2020-11-19T11:47:25.5703619"
          },
          {
              "searchText": "\"Mauris venenati",
              "filePath": "data/lorem-ipsum.txt",
              "searchDate": "2020-11-19T11:47:20.874796"
          },
      ]

