package com.mycompany.app;

import com.hashicorp.cdktf.*;

public class Main
{
    public static void main(String[] args) {
        final App app = new App();
        MainStack stack = new MainStack(app, Constant.GITHUB_REPOSITORY);
        provisionTFCloud(stack, Constant.TF_CLOUD_ORGANIZATION, Constant.TF_CLOUD_WORKSPACE, Constant.TF_CLOUD_TOKEN);
        app.synth();
    }

    private static void provisionTFCloud(
            TerraformStack stack, final String organization, final String workspace, final String token) {
        new CloudBackend(stack, CloudBackendProps.builder()
                .hostname("app.terraform.io")
                .organization(organization)
                .workspaces(new NamedCloudWorkspace(workspace))
                .token(token)
                .build());
    }
}