# Info

## Plugins

## axon framework

https://plugins.jetbrains.com/plugin/18628-axon-framework

https://start.axoniq.io/

### Versions 

https://discuss.axoniq.io/t/upgrading-to-axon-framework-4-7/4661

_In this release, we have moved away from the dedicated Axon Jakarta module solution in favor of introducing specific Javax and Jakarta versions of the Framework’s JPA infrastructure components.

## docker

https://hub.docker.com/r/axoniq/axonserver

See http://localhost:8024/#

## Server 

### AWS 

https://discuss.axoniq.io/t/using-axon-in-aws/4742

## Maven jib

https://www.baeldung.com/jib-dockerizing

Ex:

https://github.com/toretest/demo1/blob/main/pom.xml

## Spring boot 

### Mix kotlin in java spring boot apps

https://www.youtube.com/watch?v=4-qOxvjjF8g

## cubernetes docker

### kubernetes ui

### docker 

minikube start
minikube dashboard


see 
example https://www.baeldung.com/dockerizing-spring-boot-application#Dockerize

see 
https://pspdfkit.com/blog/2018/how-to-use-docker-compose-to-run-multiple-instances-of-a-service-in-development/

see 
https://www.baeldung.com/spring-boot-minikube

https://www.baeldung.com/docker-local-images-minikube

https://www.sivalabs.in/kubernetes-ingress/

https://kubernetes.io/docs/tasks/access-application-cluster/ingress-minikube/

kubectl get pods -n ingress-nginx

```
docker build --tag=customerorder:latest .
docker run -p8080:8080 customerorder:latest

$> docker inspect customerorder
$> docker stop customerorder
$> docker rm customerorder

# virker
> minikube image build -t customerorder -f ./Dockerfile . 
> kubectl run third-container --image=customerorder:latest --image-pull-policy=Never --restart=Never


# virker  (virker ikke når : latest eller mangler)
kubectl create deployment customerorder1 --image=customerorder:1
kubectl expose deployment customerorder1 --type=LoadBalancer --port=8080
(kubectl expose deployment demo-backend --type=NodePort)
kubectl get services

kubectl scale -n default deployment customerorder1 --replicas=4

kubectl get deployments
kubectl get pods
kubectl logs <pod id>

# Now, we can call our backend service for the first time:
minikube service customerorder1
 
kubectl delete service customerorder1
kubectl delete deployment customerorder1
```
