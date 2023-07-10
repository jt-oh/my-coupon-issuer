# my-coupon-issuer
쿠폰 발급 서버 실습

## Notion
[Notion](https://www.notion.so/Coupon-Issuer-4331608199344ca3a9180355d63a74a2)

## Usage

### Run A Container In Development Mode
```bash
docker build -t my-coupon-issuer:dev --target=dev . &&
docker run -it --rm -p 8080:8080 my-coupon-issuer:dev
```

### Build and Push Container Image
```bash
./build_and_push_container_image.sh
```

### Deploy Productions on k8s
```bash
./deploy.sh
```