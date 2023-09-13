package hello.core.singleton;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SingletonService {

    // 자기자신을 선언하고 주로 참조명도 다음과 같이한다
    // 자기자신을 내부에서 가지고있는데 상수로 가짐
    // -> 클래스 레벨에 있기 때문에 하나만 존재함
    private static final SingletonService instance = new SingletonService();

    // 조회할 때! -> 얘말고는 조회 못함
    public static SingletonService getInstance() {
        return instance;
    }

    //밖에서 함부로 new 못하게 private 생성자를 만든다.
    //이제 다른곳에서 생성못함
    private SingletonService() {
    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }


}
