Install minikube
https://minikube.sigs.k8s.io/docs/start/

Optional: Install Lens
https://snapcraft.io/kontena-lens

Start minikube
minikube start --addons=ingress --cpus=2 --cni=flannel --install-addons=true --kubernetes-version=stable --memory=4g

Step 1: Start AWX kubernetes orchestrators
kubectl apply -f https://raw.githubusercontent.com/ansible/awx-operator/0.13.0/deploy/awx-operator.yaml

Step 2: Run awx.yml to start AWX cluster
kubectl apply -f ./awx/awx.yml

Step 3: Get ingress ip and url and Add to host file
kubectl get ingresses awx-ingress  | grep -v NAME | awk -F' ' '{print $4 " " $3 }' |  sudo tee -a /etc/hosts
