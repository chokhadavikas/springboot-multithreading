# springboot-multithreading

## build docker image and push it to docker hub using Windows powershell

# build - 
docker build -t springboot-multithreading:latest .
# start minikube
minikube start
# powershell to read images locally means (to use minikube docker demon) run below
& minikube -p minikube docker-env | Invoke-Expression
# login to docker hub , create tag and push image
docker login -u "username" -p "password" docker.io    
docker tag springboot-multithreading <your docker hub username>/springboot-multithreading:latest
docker push <your docker hub username>/springboot-multithreading:version

# create deployment 
kubectl apply -f deployment.yaml
# check deployments
kubectl get deployments
# check pods
kubectl get pods
# check logs
kubectl logs {name of the pod}

