From openjdk:8
copy ./library-parent/library-web/target/library-web-1.0.1-SNAPSHOT.jar library-web-1.0.1-SNAPSHOT.jar
CMD ["java","-jar","library-web-1.0.1-SNAPSHOT.jar"]