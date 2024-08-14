# Bookstore API

这是一个简单的 RESTful API 服务，用于管理书店的图书和购物车。用户可以浏览图书、添加图书到购物车、查看购物车中的商品列表以及获取购物车的总价格。

## 目录结构

```plaintext
可自行补充
```

## 技术栈

- **Spring Boot**: 用于构建 RESTful 服务。
- **Spring Data JPA**: 用于数据持久化。
- **Swagger 3**: 用于 API 文档生成。

**选择理由：**
1. 电商项目业务逻辑较复杂、功能场景较多，相较于Quarkus，SpringBoot生态丰富且成熟。
2. SpringBoot开发者熟悉且开发风险低。
3. Quarkus响应式编程，代码可读性及质量不好控制。


## API 接口

### 图书操作

- **URL**: `/api/books`
- **Method**: POST
- **Parameters**:
    - **RequestBody**: `BookDto` (图书DTO对象)
        - `title`: String (必填)
        - `author`: String (必填)
        - `price`: Double (必填)
        - `category`: String (选填)
- **Returns**: `Long` (图书ID)

**Example Request**:

```json
{
  "title": "The Great Gatsby",
  "author": "F. Scott Fitzgerald",
  "price": 10.99,
  "isbn": "978-0743273565"
}
```

**Example Response**:

```json
{
  "code": 200,
  "message": "OK",
  "data": 1
}
```

#### 获取分页图书列表

- **URL**: `/api/books`
- **Method**: GET
- **Parameters**:
    - `page`: Integer (默认值: 0, 表示第一页)
    - `size`: Integer (默认值: 10, 每页显示数量)
- **Returns**: `Page<Book>` (图书列表)

**Example Request**:

```bash
GET /api/books?page=0&size=10
```

**Example Response**:

```json
{
  "code": 200,
  "message": "OK",
  "data": {
    "content": [
      {
        "id": 1,
        "title": "t1",
        "author": "a1",
        "price": 1.5,
        "category": "c1"
      }
    ],
    "pageable": {
      "sort": {
        "empty": true,
        "unsorted": true,
        "sorted": false
      },
      "offset": 0,
      "pageSize": 10,
      "pageNumber": 0,
      "unpaged": false,
      "paged": true
    },
    "totalPages": 1,
    "totalElements": 1,
    "last": true,
    "number": 0,
    "sort": {
      "empty": true,
      "unsorted": true,
      "sorted": false
    },
    "size": 10,
    "numberOfElements": 1,
    "first": true,
    "empty": false
  }
}
```

---

### 购物车操作

#### 添加图书到购物车

- **URL**: `/api/shopping-carts`
- **Method**: POST
- **Parameters**:
    - **Query Parameters**:
        - `userId`: String (用户ID)
        - `bookId`: Long (图书ID)
        - `quantity`: Integer (数量)
- **Returns**: `Boolean` (是否成功添加)

**Example Request**:

```bash
POST /api/shopping-carts?userId=tom&bookId=1&quantity=2
```

**Example Response**:

```json
{
  "code": 200,
  "message": "OK",
  "data": true
}
```

#### 获取用户的购物车详情

- **URL**: `/api/shopping-carts/{userId}`
- **Method**: GET
- **Parameters**:
    - **Path Parameters**:
        - `userId`: String (用户ID)
- **Returns**: `ShoppingCartDto` (购物车详情DTO)

**Example Request**:

```bash
GET /api/shopping-carts/tom
```

**Example Response**:

```json
{
  "code": 200,
  "message": "OK",
  "data": {
    "id": 1,
    "userId": "tom",
    "items": [
      {
        "id": 1,
        "book": {
          "id": 1,
          "title": "t1",
          "author": "a1",
          "price": 1.5,
          "category": "c1"
        },
        "quantity": 46
      }
    ]
  }
}
```

#### 获取用户的购物车总价

- **URL**: `/api/shopping-carts/{userId}/total-price`
- **Method**: GET
- **Parameters**:
    - **Path Parameters**:
        - `userId`: String (用户ID)
- **Returns**: `Double` (购物车总价)

**Example Request**:

```bash
GET /api/shopping-carts/tom/total-price
```

**Example Response**:

```json
{
  "code": 200,
  "message": "OK",
  "data": 69
}
```

---

## 使用指南

1. **启动服务**:
    - 确保安装了 Java 17 和 Maven 3.6+。
    - 执行 `mvn spring-boot:run` 或者直接运行 `src/main/java/com/example/bookstore/BookstoreApplication.java`。

2. **访问 API**:
    - 使用 Postman 或类似工具访问上面列出的 API URL。
    - 或者访问 `http://localhost:8080/swagger-ui/index.html` 以查看和测试 API。
    - 测试时注意：由于使用的内存数据库，重启服务后，数据会被清空
    
## 测试

- **BookServiceTest.java**: 包含对 `IBookService` 的单元测试。
- **ShoppingCartServiceTest.java**: 包含对 `IShoppingCartService` 的单元测试。

### 运行测试

- 使用 IDE 的内置测试运行器来运行这些测试类。

## 开发环境配置

- **IDE**: IntelliJ IDEA
- **Database**: H2，后台地址：http://localhost:8080/h2-console
