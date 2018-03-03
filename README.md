# Compilador de C para Assembly.

# Gerar Analisador Léxico

```sh
$ java -jar libs/jflex.jar --noinputstreamctor -d ./java/src/main/compiler/generated ./spec/Scanner.flex
```

# Gerar Analisador Sintático

```sh
$ java -jar libs/cup.jar -compact_red -expect 10000 -package compiler.generated -destdir ./java/src/main/compiler/generated -parser Parser ./spec/Parser.cup
```
