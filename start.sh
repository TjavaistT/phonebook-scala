IMAGE_NAME=phonebook_img
CONTAINER_NAME=phonebook_cont
CONTAINER_ID=`docker container ls -a -f name=$CONTAINER_NAME -q`

run_container () {
  docker container run --name $CONTAINER_NAME -e POSTGRES_PASSWORD=password -d -p 5400:5432 $IMAGE_NAME
}

delete_container () {
  docker container stop $CONTAINER_ID && docker container rm $CONTAINER_ID
}

if [ -z "$CONTAINER_ID" ]
then
  run_container
else
  delete_container
  run_container
fi

cd sbt_stage || exit
sbt run