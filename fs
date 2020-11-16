LINE_KEY=$(grep API_KEY .env | xargs)
IFS='=' read -ra VAR <<< "$LINE_KEY"
API_KEY=${VAR[1]}

curl https://identitytoolkit.googleapis.com/v1/accounts:signUp?key=$API_KEY \
-H 'Content-Type: application/json' --data-binary '{"returnSecureToken":true}' \
>> fs.output

