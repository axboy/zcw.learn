package local.zcw.service;

import org.springframework.security.oauth2.provider.*;

import java.util.List;

/**
 * Created by zcw on 2017/02/22.
 * 使用jdbc的方式存client，不初始化在内存中
 * TODO 未完
 */
public class ClientService{

}
//
//public class ClientService implements ClientDetailsService, ClientRegistrationService {
//
//    /**
//     * Load a client by the client id. This method must not return null.
//     *
//     * @param clientId The client id.
//     * @return The client details (never null).
//     * @throws ClientRegistrationException If the client account is locked, expired, disabled, or invalid for any other reason.
//     */
//    @Override
//    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
//        return null;
//    }
//
//    @Override
//    public void addClientDetails(ClientDetails clientDetails) throws ClientAlreadyExistsException {
//
//    }
//
//    @Override
//    public void updateClientDetails(ClientDetails clientDetails) throws NoSuchClientException {
//
//    }
//
//    @Override
//    public void updateClientSecret(String clientId, String secret) throws NoSuchClientException {
//
//    }
//
//    @Override
//    public void removeClientDetails(String clientId) throws NoSuchClientException {
//
//    }
//
//    @Override
//    public List<ClientDetails> listClientDetails() {
//        return null;
//    }
//}
