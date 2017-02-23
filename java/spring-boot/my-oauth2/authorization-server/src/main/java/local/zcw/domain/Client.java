package local.zcw.domain;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * Created by zcw on 2017/02/22.
 * TODO 未完
 */
public class Client{}


//
//@Entity
//@Table(name = "client")
//public class Client implements ClientDetails {
//
//    @Id
//    @Column(name = "id")
//    @GeneratedValue(generator = "hibernate-uuid")
//    @GenericGenerator(name = "hibernate-uuid", strategy = "uuid")
//    private String id;
//
//    @Column(name = "client_id")
//    private String client_id;
//
//    @Column(name = "client_secret")
//    private String client_secret;
//
//    private Set<String> scopes;
//
//    private Set<String> resourceIds;
//
//    private Set<String> grantTypes;
//
//    /**
//     * The client id.
//     *
//     * @return The client id.
//     */
//    @Override
//    public String getClientId() {
//        return this.client_id;
//    }
//
//    /**
//     * The resources that this client can access. Can be ignored by callers if empty.
//     *
//     * @return The resources of this client.
//     */
//    @Override
//    public Set<String> getResourceIds() {
//        return this.resourceIds;
//    }
//
//    /**
//     * Whether a secret is required to authenticate this client.
//     *
//     * @return Whether a secret is required to authenticate this client.
//     */
//    @Override
//    public boolean isSecretRequired() {
//        return false;
//    }
//
//    /**
//     * The client secret. Ignored if the {@link #isSecretRequired() secret isn't required}.
//     *
//     * @return The client secret.
//     */
//    @Override
//    public String getClientSecret() {
//        return this.client_secret;
//    }
//
//    /**
//     * Whether this client is limited to a specific scope. If false, the scope of the authentication request will be
//     * ignored.
//     *
//     * @return Whether this client is limited to a specific scope.
//     */
//    @Override
//    public boolean isScoped() {
//        return false;
//    }
//
//    /**
//     * The scope of this client. Empty if the client isn't scoped.
//     *
//     * @return The scope of this client.
//     */
//    @Override
//    public Set<String> getScope() {
//        return this.scopes;
//    }
//
//    /**
//     * The grant types for which this client is authorized.
//     *
//     * @return The grant types for which this client is authorized.
//     */
//    @Override
//    public Set<String> getAuthorizedGrantTypes() {
//        return this.grantTypes;
//    }
//
//    /**
//     * The pre-defined redirect URI for this client to use during the "authorization_code" access grant. See OAuth spec,
//     * section 4.1.1.
//     *
//     * @return The pre-defined redirect URI for this client.
//     */
//    @Override
//    public Set<String> getRegisteredRedirectUri() {
//        return null;
//    }
//
//    /**
//     * Returns the authorities that are granted to the OAuth client. Cannot return <code>null</code>.
//     * Note that these are NOT the authorities that are granted to the user with an authorized access token.
//     * Instead, these authorities are inherent to the client itself.
//     *
//     * @return the authorities (never <code>null</code>)
//     */
//    @Override
//    public Collection<GrantedAuthority> getAuthorities() {
//        return null;
//    }
//
//    /**
//     * The access token validity period for this client. Null if not set explicitly (implementations might use that fact
//     * to provide a default value for instance).
//     *
//     * @return the access token validity period
//     */
//    @Override
//    public Integer getAccessTokenValiditySeconds() {
//        return null;
//    }
//
//    /**
//     * The refresh token validity period for this client. Null for default value set by token service, and
//     * zero or negative for non-expiring tokens.
//     *
//     * @return the refresh token validity period
//     */
//    @Override
//    public Integer getRefreshTokenValiditySeconds() {
//        return null;
//    }
//
//    /**
//     * Test whether client needs user approval for a particular scope.
//     *
//     * @param scope the scope to consider
//     * @return true if this client does not need user approval
//     */
//    @Override
//    public boolean isAutoApprove(String scope) {
//        return false;
//    }
//
//    /**
//     * Additional information for this client, not needed by the vanilla OAuth protocol but might be useful, for example,
//     * for storing descriptive information.
//     *
//     * @return a map of additional information
//     */
//    @Override
//    public Map<String, Object> getAdditionalInformation() {
//        return null;
//    }
//}
