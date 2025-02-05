# service-core
mian service rest api


curl --location 'http://localhost:8080/api/v1/auth/singin' \
--header 'Content-Type: application/json' \
--data-raw '{
  "email": "admin@mail.com",
  "password": "password"
}'


curl --location 'http://localhost:8080/api/v1/user/getAll' \
--header 'Authorization: ••••••'