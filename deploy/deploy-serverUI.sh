docker stop my-blog-serverui
docker rm my-blog-serverui
docker rmi blog-serverui
docker build -t blog-serverui .
docker run -tid --restart=always --name my-blog-serverui -p 81:80 blog-serverui