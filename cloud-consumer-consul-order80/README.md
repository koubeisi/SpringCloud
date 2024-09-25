# API

```shell
# exception
curl http://localhost/circuit/-1
# normal
curl http://localhost/circuit/1
# timeout
curl http://localhost/circuit/101
```

# consul

```shell
./consul agent -dev -client=0.0.0.0 -http-port=58500 -ui
```