# LeyaoAppService

mvn spring-boot:run

## getEvents
http://localhost:8088/v1/service/event/getEvents?sEventTypeCd=1&page=1&rows=7

## getTEventSummaryByType
#### Image("Image", 1), Audio("Audio", 2), Video("Video", 3);
http://localhost:8088/v1/service/event/getTEventSummaryByType

http://localhost:8088/v1/service/event/getTEventSummaryByType?sEventTypeCd=1

http://localhost:8088/v1/service/event/getTEventSummaryByType?sEventTypeCd=2&page=1&rows=7

## getTEventSummaryByCategory
#### Activity("Activity", 1), Education("Education", 2), Share("Share", 3), Album("Album", 4);
http://localhost:8088/v1/service/event/getTEventSummaryByCategory

http://localhost:8088/v1/service/event/getTEventSummaryByCategory?sEventCategoryCd=1

http://localhost:8088/v1/service/event/getTEventSummaryByCategory?sEventCategoryCd=1&page=1&rows=7

## getVerifyCode
curl -XPOST 'localhost:8088/v1/service/user/getVerifyCode?phoneNum=13000000000 '


## test
http://localhost:8088/v1/test/getHsEvent

http://localhost:8088/v1/test/getAllSEventActive