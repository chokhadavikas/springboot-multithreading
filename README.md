# springboot-multithreading

## build docker image and push it to docker hub using Windows powershell


# start minikube
minikube start
# powershell to read images locally means (to use minikube docker demon) run below, if you have set the imagePullPolicy: IfNotPresent
minikube docker-env
& minikube -p minikube docker-env --shell powershell | Invoke-Expression

# build -
docker build -t springboot-multithreading:latest .

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

# create services
kubectl apply -f service.yaml
# check services
kubectl get services
# or
kubectl get svc
# output of command with node port
# In ports section you will see the port where service is exposed, combined it with node ip and run the app in postman
# you can you below command to check node ip
kubectl get nodes -o wide
minikube ip
# check dashboard
minikube dashboard


