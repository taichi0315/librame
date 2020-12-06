# librame
Scala言語で書かれたWeb開発用ライブラリです。DDDを意識した設計になっています。  
最終的にはOSSにしたい。。

## 採用技術
- Domain Layer
  - Password Hash
    - Silhouette
- Application Service Layer
  - Extensible effects
    - Eff
    - Eff Cats Effect
- Secondary Adapter Layer
  - RDB
    - Doobie
    - Slick
  - KVS
    - Play Redis
- Primary Adapter Layer
  - Play Framework

## サンプルコード
librameを用いたプロジェクトのサンプルコードは以下のリポジトリに実装されています。

[taichi0315/scala-ddd-auth-base](https://github.com/taichi0315/scala-ddd-auth-base)

## 参考リンク
### 書籍
- [ドメイン駆動設計入門 ボトムアップでわかる！ドメイン駆動設計の基本](https://www.shoeisha.co.jp/book/detail/9784798150727)
- [ドメイン駆動設計 モデリング/実装ガイド](https://little-hands.booth.pm/items/1835632)
### 資料
- [scala-on-ddd](https://speakerdeck.com/crossroad0201/scala-on-ddd)
- [Password Hashing](https://www.silhouette.rocks/docs/passwordhasher)
### リポジトリ
- [crossroad0201/ddd-on-scala](https://github.com/crossroad0201/ddd-on-scala)
- [t2v/play2-auth](https://github.com/t2v/play2-auth)
