package BanpoXi.Dong.repository;

import BanpoXi.Dong.domain.Member;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;
import java.util.Optional;


public class JdbcMemberRepository implements MemberRepository {


    private final DataSource dataSource;

    public JdbcMemberRepository(DataSource dataSource) {
        this.dataSource = dataSource;

    }

    @Override
    public Member save(Member member) {
        String sql = "insert into member(name) values(?)";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = dataSource.getConnection();
            pstmt = conn.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, member.getName());
            pstmt.executeUpdate();
            rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                member.setId(rs.getLong(1));
            } else {
                throw new SQLException("id 조회 실패");
            }
            return member;
            /*
            영한님의 강의
            - 여기서 실패하면 익셉션을 미친듯이 떤진다
            - 익셉션 엄청 던지니까 트라이캐치 잘해야된다
            - 자원들 쓴것들, 릴리즈 해줘야한다
            - 외부자원들(데이터베이스들) 쓰고 꼭 전원꺼줘야한다(자원반납을 해야한다)
            - 만약 안하면 >> 큰일납니다 증말
            - 커넥션 계속쌓이다가 장애 큰장애 나니까 잘 닫아줘라


             */

        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {



            close(conn, pstmt, rs);
        }
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Member> findByName(String name) {
        return Optional.empty();
    }

    @Override
    public List<Member> findAll() {
        return null;
    }
}
