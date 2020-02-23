package com.ci.claim;

import com.ci.domain.CreateClaimRequest;
import com.ci.dto.ClaimDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import static java.util.Objects.nonNull;

@Repository
public class ClaimsDetailDAO {

    private final SimpleJdbcInsert simpleJdbcInsert;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ClaimsDetailDAO(DataSource dataSource) {
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("claimdetails")
                .usingGeneratedKeyColumns("id");
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public long createClaim(CreateClaimRequest request) {
        MapSqlParameterSource input = new MapSqlParameterSource();
        input.addValue("firstname", request.getFirstName());
        input.addValue("lastname", request.getLastName());
        input.addValue("emailid", request.getEmailId());
        input.addValue("policynumber", request.getPolicyNumber());
        input.addValue("originalflightnumber", request.getOriginalFlightNumber());
        input.addValue("reasonofdelay", request.getReasonOfDelay());
        input.addValue("consequenceofdelay", request.getConsequenceOfDelay());
        input.addValue("replacementflightno", request.getReplacementFlightDetail());

        if (nonNull(request.getReplacementFlightDetail()) && nonNull(request.getReplacementFlightDetail().getDate())) {
            input.addValue("replacementflightdate", convertToTimestamp(request.getReplacementFlightDetail().getDate()));
        }

        input.addValue("originalflightdate", convertToTimestamp(request.getDateOfOriginalFlight()));
        input.addValue("delaytime", request.getDelayTime());

        return simpleJdbcInsert.executeAndReturnKey(input).longValue();
    }

    public List<ClaimDetail> getAllClaims() {
        String sql = "select id,firstname,lastname,emailid,policynumber,originalflightnumber,reasonofdelay,consequenceofdelay,replacementflightno,originalflightdate,delaytime,replacementflightdate from claimdetails ";
        return jdbcTemplate.query(sql, new ClaimDetailsRowMapper());
    }

    public List<ClaimDetail> getClaimsById(long claimOrderNo) {
        String sql = "select id,firstname,lastname,emailid,policynumber,originalflightnumber,reasonofdelay,consequenceofdelay,replacementflightno,originalflightdate,delaytime,replacementflightdate from claimdetails where id = ? ";

        return jdbcTemplate.query(sql, new ClaimDetailsRowMapper(), claimOrderNo);
    }

    public List<ClaimDetail> getClaimsByEmailAddress(String emailId) {
        String sql = "select id,firstname,lastname,emailid,policynumber,originalflightnumber,reasonofdelay,consequenceofdelay,delaytime,replacementflightno,originalflightdate,replacementflightdate from claimdetails where emailid = ? ";

        return jdbcTemplate.query(sql, new ClaimDetailsRowMapper(), emailId);
    }

    private Timestamp convertToTimestamp(Date dateOfOriginalFlight) {
        return Timestamp.from(dateOfOriginalFlight.toInstant());
    }

    private class ClaimDetailsRowMapper implements RowMapper<ClaimDetail> {
        @Override
        public ClaimDetail mapRow(ResultSet rs, int i) throws SQLException {
            return ClaimDetail.builder()
                    .withFirstName(rs.getString("firstname"))
                    .withLastName(rs.getString("lastname"))
                    .withEmailId(rs.getString("emailid"))
                    .withPolicyNumber(rs.getString("policynumber"))
                    .withOriginalFlightNumber(rs.getString("originalflightnumber"))
                    .withReasonOfDelay(rs.getString("reasonofdelay"))
                    .withConsequenceOfDelay(rs.getString("consequenceofdelay"))
                    .withDelayTime(rs.getString("delaytime"))
                    .withOriginalFlightDate(rs.getTimestamp("originalflightdate"))
                    .withReplacementFlightDate(rs.getTimestamp("replacementflightdate"))
                    .withReplacementFlightNo(rs.getString("replacementflightno"))
                    .build();
        }
    }
}