package com.beijunyi.parallelgit.web.protocol;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import javax.annotation.Nonnull;

import com.beijunyi.parallelgit.filesystem.GitFileSystem;
import com.beijunyi.parallelgit.web.workspace.Workspace;

public class MoveFileHandler implements RequestHandler {

  @Override
  public String getType() {
    return "move-file";
  }

  @Nonnull
  @Override
  public ServerResponse handle(@Nonnull ClientRequest request, @Nonnull Workspace workspace) throws IOException {
    GitFileSystem gfs = workspace.getFileSystem();
    Path source = gfs.getPath(request.getString("source"));
    Path destination = gfs.getPath(request.getString("directory")).resolve(request.getString("filename"));
    Files.move(source, destination);
    return request.respond().ok();
  }
}