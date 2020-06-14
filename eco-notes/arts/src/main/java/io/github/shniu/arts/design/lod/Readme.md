# LOD 迪米特法则

也可以叫最小知识原则，英文翻译为：The Least Knowledge Principle

描述：

每个模块（unit）只应该了解那些与它关系密切的模块（units: only units “closely” related to the current unit）的有限知识（knowledge）。
或者说，每个模块只和自己的朋友“说话”（talk），不和陌生人“说话”（talk）。

可以解读为两个方面：

1. 不该有直接依赖关系的类之间，不要有依赖
2. 有依赖关系的类之间，尽量只依赖必要的接口（也就是定义中的“有限知识”）

`spider` 包中是 `不该有直接依赖关系的类之间，不要有依赖` 的例子

`serializer` 包是 `有依赖关系的类之间，尽量只依赖必要的接口（也就是定义中的“有限知识”）` 的例子

基于最小接口而非最大实现编程
