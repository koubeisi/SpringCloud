# API

```shell
# exception
curl http://localhost/circui/-1
# normal
curl http://localhost/circui/1
# timeout
curl http://localhost/circui/100
```

# consul

```shell
./consul agent -dev -client=0.0.0.0 -http-port=58500 -ui
```