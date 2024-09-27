# API

```shell
# exception
curl http://localhost/feign/-1
# normal
curl http://localhost/feign/1
# timeout
curl http://localhost/feign/101
```

# consul

```shell
./consul agent -dev -client=0.0.0.0 -http-port=58500 -ui
```